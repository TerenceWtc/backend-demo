package org.terence.backend.common.exception.jwt;

import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.BaseException;

/**
 * @author terence
 * @since 2019/2/25 9:32
 */
public class GroupNotAssignedException extends BaseException {

    private static final long serialVersionUID = 8709576517150676258L;

    public GroupNotAssignedException(String message) {
        super(message, ExceptionConstant.GROUP_NOT_ASSIGNED_CODE);
    }
}
