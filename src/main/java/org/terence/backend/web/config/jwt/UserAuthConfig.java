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

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.key.public}")
    private String publicKeyPath;

    @Value("${jwt.key.private}")
    private String privateKeyPath;
}
