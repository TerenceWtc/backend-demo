package org.terence.backend.web.filter;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.utils.NullValueUtil;
import org.terence.backend.common.utils.jwt.IUserJwtInfo;
import org.terence.backend.common.utils.jwt.JwtHelper;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author terence
 * @since 2019/2/19 14:19
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final UserAuthConfig userAuthConfig;

    @Autowired
    public JwtAuthenticationFilter(UserAuthConfig userAuthConfig) {
        this.userAuthConfig = userAuthConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestTokenHeader = httpServletRequest.getHeader(userAuthConfig.getAccessHeader());
        IUserJwtInfo userIwtInfo = null;
        if (!NullValueUtil.judgeNull(requestTokenHeader)) {
            String authToken = requestTokenHeader.substring(userAuthConfig.getStart().length());
            try {
                userIwtInfo = JwtHelper.getInfoFromToken(authToken, userAuthConfig.getPublicKeyPath());
            } catch (ExpiredJwtException e) {
                logger.error("Token Expired!");
                httpServletResponse.setStatus(ExceptionConstant.TOKEN_EXPIRED_CODE);
            } catch (Exception e) {
                logger.error("Invalid token: {}", authToken, e);
            }
        } else {
            logger.warn("couldn't find Bearer string, will ignore the header");
        }

        if (userIwtInfo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.debug("security context was null, so authorizing user");
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userIwtInfo, null, new ArrayList<>());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            logger.info("authorized user '{}', setting security context", userIwtInfo.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
