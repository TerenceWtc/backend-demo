package org.terence.backend.common.exception.auth;

import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.BaseException;

/**
 * @author terence
 * @since 2019/2/25 14:21
 */
public class UserInvalidException extends BaseException {

    private static final long serialVersionUID = 778133757609210391L;

    public UserInvalidException(String message) {
        super(message, ExceptionConstant.USER_INVALID_CODE);
    }

    public UserInvalidException(String message, int code) {
        super(message, code);
    }
}
