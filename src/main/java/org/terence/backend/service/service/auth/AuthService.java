package org.terence.backend.service.service.auth;

import org.terence.backend.service.vo.auth.Token;
import org.terence.backend.service.vo.base.ObjectResponse;

/**
 * @author terence
 * @since 2019/2/25 16:09
 */
public interface AuthService {

    Token login(String username, String password);

    Token register(String username, String password, String name, String email);

    String refresh(String refreshToken);

    Boolean verifyUsername(String username);
}
