package org.terence.backend.common.exception.jwt;

import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.BaseException;

/**
 * @author terence
 * @since 2019/2/28 17:01
 */
public class TokenException extends BaseException {

    private static final long serialVersionUID = -2371292269850442945L;

    public TokenException(String message) {
        super(message, ExceptionConstant.TOKEN_EXCEPTION);
    }
}
