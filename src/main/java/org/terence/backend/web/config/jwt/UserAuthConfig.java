package org.terence.backend.web.config.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author terence
 * @since 2019/2/18 17:39
 */
@Component
@Configuration
@Data
public class UserAuthConfig {

    @Value("${jwt.start}")
    private String start;

    @Value("${jwt.accessHeader}")
    private String accessHeader;

    @Value("${jwt.accessExpiration}")
    private int accessExpiration;

    @Value("${jwt.refreshHeader}")
    private String refreshHeader;

    @Value("${jwt.refreshExpiration}")
    private int refreshExpiration;

    @Value("${jwt.key.public}")
    private String publicKeyPath;

    @Value("${jwt.key.private}")
    private String privateKeyPath;
}
