package com.demaru.global.security.exception;

import com.demaru.global.error.exception.BusinessException;
import com.demaru.global.security.exception.errorCode.SecurityErrorCode;

public class InvalidTokenException extends BusinessException {
    public static final InvalidTokenException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() { super(SecurityErrorCode.INVALID_TOKEN); }
}
