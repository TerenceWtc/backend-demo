package org.terence.backend.service.impl.auth;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.auth.UserInvalidException;
import org.terence.backend.common.exception.jwt.TokenException;
import org.terence.backend.common.exception.jwt.TokenExpiredException;
import org.terence.backend.common.utils.jwt.IUserJwtInfo;
import org.terence.backend.common.utils.jwt.JwtHelper;
import org.terence.backend.common.utils.jwt.UserJwtInfo;
import org.terence.backend.dao.entity.admin.SysUser;
import org.terence.backend.service.service.admin.UserService;
import org.terence.backend.service.service.auth.AuthService;
import org.terence.backend.service.vo.auth.Token;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author terence
 * @since 2019/2/25 16:09
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserAuthConfig userAuthConfig;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserAuthConfig userAuthConfig, AuthenticationManager authenticationManager, UserService userService) {
        this.userAuthConfig = userAuthConfig;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Token login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (UsernameNotFoundException e) {
            // thrown exception when username is not found
            throw new UserInvalidException("Username not found!", ExceptionConstant.USERNAME_NOT_FOUND_CODE);
        } catch (BadCredentialsException e) {
            // authorization failed, usually invalid password
            throw new UserInvalidException("Bad credentials!", ExceptionConstant.BAD_CREDENTIALS_CODE);
        }
        SysUser sysUser = userService.getUserByUsername(username);
        String accessToken = generateToken(sysUser, userAuthConfig.getAccessExpiration());
        String refreshToken = generateToken(sysUser, userAuthConfig.getRefreshExpiration());
        return new Token(accessToken, refreshToken);
    }

    @Override
    public Token register(String username, String password, String name, String email) {
        SysUser sysUser = new SysUser(username, password, name, email, Date.valueOf(LocalDate.now()), "REGISTER");
        SysUser result = userService.registerUser(sysUser);
        String accessToken = generateToken(result, userAuthConfig.getAccessExpiration());
        String refreshToken = generateToken(result, userAuthConfig.getRefreshExpiration());
        return new Token(accessToken, refreshToken);
    }

    @Override
    public String refresh(String refreshToken) {
        IUserJwtInfo userJwtInfo;
        try {
            userJwtInfo = JwtHelper.getInfoFromToken(refreshToken, userAuthConfig.getPublicKeyPath());
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("refresh token expired");
        } catch (Exception e) {
            throw new TokenException("invalid token");
        }
        SysUser sysUser = new SysUser(Long.parseLong(userJwtInfo.getId()), userJwtInfo.getName(), userJwtInfo.getUsername());
        return generateToken(sysUser, userAuthConfig.getAccessExpiration());
    }

    @Override
    public Boolean verifyUsername(String username) {
        return userService.verifyUsername(username);
    }

    private String generateToken(SysUser sysUser, int expiration) {
        String token = null;
        try {
            UserJwtInfo userJwtInfo = new UserJwtInfo(sysUser.getId() + "", sysUser.getUsername(), sysUser.getName());
            token = JwtHelper.generateToken(userJwtInfo, userAuthConfig.getPrivateKeyPath(), expiration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
