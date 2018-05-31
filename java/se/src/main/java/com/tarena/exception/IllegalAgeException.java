package com.tarena.exception;

import java.lang.Exception;
/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description 自定义异常
 */
public class IllegalAgeException extends Exception {
    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException() {
        super();
    }

    public IllegalAgeException(String message) {
        super(message);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }

    protected IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
