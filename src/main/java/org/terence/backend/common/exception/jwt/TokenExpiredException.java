package org.terence.backend.common.exception.jwt;

import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.BaseException;

/**
 * @author terence
 * @since 2019/2/19 14:03
 */
public class TokenExpiredException extends BaseException {

    private static final long serialVersionUID = -2257442527436406740L;

    public TokenExpiredException(String message) {
        super(message, ExceptionConstant.TOKEN_EXPIRED_CODE);
    }
}
