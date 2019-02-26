package org.terence.backend.service.service.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.auth.UserInvalidException;
import org.terence.backend.common.utils.jwt.JwtHelper;
import org.terence.backend.common.utils.jwt.UserJwtInfo;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.service.service.admin.UserService;
import org.terence.backend.service.service.auth.AuthService;
import org.terence.backend.service.vo.base.ObjectResponse;
import org.terence.backend.web.config.jwt.UserAuthConfig;

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
    public ObjectResponse<String> getToken(String username, String password) {
        String token = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (UsernameNotFoundException e) {
            // 账号不存在时抛出的异常
            throw new UserInvalidException("Username not found!", ExceptionConstant.USERNAME_NOT_FOUND_CODE);
        } catch (BadCredentialsException e) {
            // 认证失败，一般是密码错误
            throw new UserInvalidException("Bad credentials!", ExceptionConstant.BAD_CREDENTIALS_CODE);
        }
        User user = userService.getUserByUsername(username);
        try {
            UserJwtInfo userJwtInfo = new UserJwtInfo(user.getId() + "", user.getUsername(), user.getName());
            token = JwtHelper.generateToken(userJwtInfo, userAuthConfig.getPrivateKeyPath(), userAuthConfig.getExpiration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ObjectResponse<>(token);
    }
}
