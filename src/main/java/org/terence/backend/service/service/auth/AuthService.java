package org.terence.backend.service.service.auth;

import org.terence.backend.service.vo.base.ObjectResponse;

/**
 * @author terence
 * @since 2019/2/25 16:09
 */
public interface AuthService {

    ObjectResponse<String> getToken(String username, String password);
}
