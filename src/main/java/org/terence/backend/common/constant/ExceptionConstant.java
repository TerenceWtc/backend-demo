package org.terence.backend.common.constant;

/**
 * @author terence
 * @since 2019/2/19 11:35
 */
public class ExceptionConstant {

    public final static int OTHER_CODE = 500;

    // exception code in utils
    public final static int NULL_VALUE_CODE = 60101;

    public final static String NULL_VALUE_MESSAGE_TMP = "Null exception!";

    // exception code in jwt
    public final static int TOKEN_EXCEPTION = 60201;

    public final static int TOKEN_EXPIRED_CODE = 60202;

    public final static int TOKEN_SIGNATURE_CODE = 60203;

    public final static int TOKEN_NULL_CODE = 60204;

    public final static int GROUP_NOT_ASSIGNED_CODE = 60205;

    // exception code in auth
    public final static int USER_INVALID_CODE = 60301;

    public final static int USERNAME_NOT_FOUND_CODE = 60302;

    public final static int BAD_CREDENTIALS_CODE = 60303;
}
