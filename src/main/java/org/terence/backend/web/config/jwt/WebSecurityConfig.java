package org.terence.backend.web.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.terence.backend.common.constant.CommonConstant;
import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.dao.repository.admin.SysUserRepository;
import org.terence.backend.web.filter.JwtAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author terence
 * @since 2019/2/20 9:51
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SysUserRepository sysUserRepository;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AccessDecisionVoter<Object> accessDecisionVoter;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public WebSecurityConfig(SysUserRepository sysUserRepository, JwtAuthenticationFilter jwtAuthenticationFilter, AccessDecisionVoter<Object> accessDecisionVoter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.sysUserRepository = sysUserRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.accessDecisionVoter = accessDecisionVoter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        // 用这个方式会导致无法抛出UsernameNotFoundException异常
//        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                // for swagger
                .antMatchers("/v2/api-docs", "/definitions/**", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagge‌​r-ui.html")
                .antMatchers(HttpMethod.POST, "/auth/login")
                .antMatchers(HttpMethod.POST, "/auth/register")
                .antMatchers(HttpMethod.POST, "/auth/refresh")
                .antMatchers(HttpMethod.GET, "/auth/verifyUsername");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .logout()
                .permitAll();*/
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager())
                .and()
                // customize entryPoint to handle exception thrown in JwtAuthenticationFilter
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .headers().cacheControl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(CommonConstant.PASSWORD_ENCORDER_SALT);
    }

    @Bean("userDetailService")
    @Override
    public UserDetailsService userDetailsService() {
        return (username) -> {
            Optional<SysUser> baseUser = sysUserRepository.findByUsername(username);
            User user;
            if (baseUser.isPresent()) {
                user = new User(baseUser.get().getUsername(), baseUser.get().getPassword(), true, true, true, true, new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("Username not found!");
            }
            return user;
        };
    }

    @Bean("accessDecisionManager")
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoterList = new ArrayList<>();
        decisionVoterList.add(accessDecisionVoter);
        return new AffirmativeBased(decisionVoterList);
    }

    // 设置hideUserNotFoundExceptions为false，使账号不存在时能抛出UsernameNotFoundException异常
    @Bean("daoAuthenticationProvider")
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
