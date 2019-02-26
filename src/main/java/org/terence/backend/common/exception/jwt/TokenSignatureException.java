package org.terence.backend.common.exception.jwt;

import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.BaseException;

/**
 * @author terence
 * @since 2019/2/19 14:07
 */
public class TokenSignatureException extends BaseException {

    private static final long serialVersionUID = -277407983178406146L;

    public TokenSignatureException(String message) {
        super(message, ExceptionConstant.TOKEN_SIGNATURE_CODE);
    }
}
