package org.terence.backend.web.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.terence.backend.service.service.auth.AuthService;
import org.terence.backend.service.vo.auth.Token;
import org.terence.backend.service.vo.base.ObjectResponse;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author terence
 * @since 2019/2/18 17:22
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    private final UserAuthConfig userAuthConfig;

    @Autowired
    public AuthController(AuthService authService, UserAuthConfig userAuthConfig) {
        this.authService = authService;
        this.userAuthConfig = userAuthConfig;
    }

    @PostMapping("/login")
    public ObjectResponse<Token> login(@RequestBody Map<String, String> body) {
        Token token = authService.login(body.get("username"), body.get("password"));
        return new ObjectResponse<>(token);
    }

    @PostMapping("/register")
    public ObjectResponse<Token> register(@RequestBody Map<String, String> body) {
        Token token = authService.register(body.get("username"), body.get("password"), body.get("name"), body.get("email"));
        return new ObjectResponse<>(token);
    }

    @GetMapping("/verifyUsername")
    public ObjectResponse<Boolean> verifyUsername(@RequestParam("username") String username) {
        boolean result = authService.verifyUsername(username);
        return new ObjectResponse<>(result);
    }

    /**
     * using npm package 'jwt-decode' in Vue to verify token
     * invoke this api to refresh access_token by refresh_token if access_token expired
     * @param request
     * @return
     */
    @PostMapping("/refresh")
    public ObjectResponse<String> refresh(HttpServletRequest request) {
        String refreshToken = request.getHeader(userAuthConfig.getRefreshHeader());
        String accessToken = authService.refresh(refreshToken);
        return new ObjectResponse<>(accessToken);
    }

    @GetMapping("/test")
    public ObjectResponse<String> test() {
        System.out.println("has token");
        return new ObjectResponse<>("test");
    }
}
