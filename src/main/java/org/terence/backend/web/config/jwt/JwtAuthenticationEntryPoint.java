package org.terence.backend.web.config.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.terence.backend.common.constant.ExceptionConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author terence
 * @since 2019/2/28 9:43
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if (httpServletResponse.getStatus() == ExceptionConstant.TOKEN_EXPIRED_CODE) {
            httpServletResponse.sendError(ExceptionConstant.TOKEN_EXPIRED_CODE, "Token expired!");
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Unauthorized");
        }
    }
}
