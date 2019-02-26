package org.terence.backend.common.exception.util;

import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.BaseException;

/**
 * @Author: terence
 * @Date: 2019/2/19 11:37
 */
public class NullValueException extends BaseException {

    private static final long serialVersionUID = 7150306492381234783L;

    public NullValueException(String message) {
        super(message, ExceptionConstant.NULL_VALUE_CODE);
    }
}
