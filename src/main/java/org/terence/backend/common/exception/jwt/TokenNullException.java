package org.terence.backend.common.exception.jwt;

import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.BaseException;

/**
 * @author terence
 * @since 2019/2/19 14:14
 */
public class TokenNullException extends BaseException {

    private static final long serialVersionUID = -1701675297032741196L;

    public TokenNullException(String message) {
        super(message, ExceptionConstant.TOKEN_NULL_CODE);
    }
}
