package org.terence.backend.common.utils.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.terence.backend.common.exception.jwt.TokenExpiredException;
import org.terence.backend.common.exception.jwt.TokenNullException;
import org.terence.backend.common.exception.jwt.TokenSignatureException;
import org.terence.backend.web.config.jwt.UserAuthConfig;

import java.rmi.server.ExportException;
import java.security.SignatureException;

/**
 * @author terence
 * @since 2019/2/18 17:39
 */
@Component
public class UserAuthUtil {

    private final UserAuthConfig userAuthConfig;

    @Autowired
    public UserAuthUtil(UserAuthConfig userAuthConfig) {
        this.userAuthConfig = userAuthConfig;
    }

    public IUserIwtInfo getInfoFromToken(String token) throws Exception {
        try {
            return JwtHelper.getInfoFromToken(token, userAuthConfig.getPublicKeyPath());
        } catch (ExportException e) {
            throw new TokenExpiredException("Token expired!");
        } catch (SignatureException e) {
            throw new TokenSignatureException("Token signature error!");
        } catch (IllegalArgumentException e) {
            throw new TokenNullException("Token is null or empty!");
        }
    }

}
