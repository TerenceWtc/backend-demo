package org.terence.backend.common.utils;

import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.common.exception.util.NullValueException;

/**
 * @author terence
 * @since 2019/2/19 11:18
 */
public class NullValueUtil {

    public static boolean judgeNull(String string) {
        return null == string || "".equals(string);
    }

    public static boolean judgeNull(Object obj) {
        return null == obj;
    }

    public static void handleNull(String string) {
        if (null == string || "".equals(string)) {
            throw new NullValueException(ExceptionConstant.NULL_VALUE_MESSAGE_TMP);
        }
    }

    public static void handleNull(Object obj) {
        if (null == obj) {
            throw new NullValueException(ExceptionConstant.NULL_VALUE_MESSAGE_TMP);
        }
    }
}
