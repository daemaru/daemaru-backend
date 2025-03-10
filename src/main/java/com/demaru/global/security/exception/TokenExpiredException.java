package com.demaru.global.security.exception;

import com.demaru.global.error.exception.BusinessException;
import com.demaru.global.security.exception.errorCode.SecurityErrorCode;

public class TokenExpiredException extends BusinessException {
    public static final TokenExpiredException EXCEPTION = new TokenExpiredException();

    private TokenExpiredException() { super(SecurityErrorCode.TOKEN_EXPIRED); }
}
