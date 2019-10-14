package org.terence.backend.service.service.auth;

import org.terence.backend.service.vo.auth.Token;
import org.terence.backend.service.vo.base.ObjectResponse;

/**
 * @author terence
 * @since 2019/2/25 16:09
 */
public interface AuthService {

    /** login by username & password, and return a jwt token
     * @param username login username
     * @param password login password
     * @return Token
     */
    Token login(String username, String password);

    /** register a new account, and return a jwt token
     * @param username login username
     * @param password login password
     * @param name real name
     * @param email email
     * @return Token
     */
    Token register(String username, String password, String name, String email);

    /** refresh the token
     * @param refreshToken a long-expiration token
     * @return String of new access token
     */
    String refresh(String refreshToken);

    /** verify if username exist
     * @param username login username
     * @return Boolean
     */
    Boolean verifyUsername(String username);
}
