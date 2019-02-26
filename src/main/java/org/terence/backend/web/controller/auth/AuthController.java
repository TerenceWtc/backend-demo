package org.terence.backend.web.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.terence.backend.service.service.auth.AuthService;
import org.terence.backend.service.vo.base.ObjectResponse;

import java.util.Map;

/**
 * @author terence
 * @since 2019/2/18 17:22
 */
@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/jwt/token")
    public ObjectResponse<String> getToken(@RequestBody Map<String, String> body) {
        return authService.getToken(body.get("username"), body.get("password"));
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("has token");
    }
}
