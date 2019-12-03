package com.core.util;

/**
 * @Description OApiException
 * @Author xg.chen
 * @Date 10:28 2019/5/9
 **/

public class OApiException extends Throwable {
    public static final int ERR_RESULT_RESOLUTION = -2;

    public OApiException(String field) {
        this(ERR_RESULT_RESOLUTION, "Cannot resolve field " + field + " from oapi resonpse");
    }

    public OApiException(int errCode, String errMsg) {
        super("error code: " + errCode + ", error message: " + errMsg);
    }
}
