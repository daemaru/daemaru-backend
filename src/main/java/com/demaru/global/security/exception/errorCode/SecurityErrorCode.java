package com.demaru.global.security.exception.errorCode;

import com.demaru.global.error.exception.ErrorProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SecurityErrorCode implements ErrorProperty {
    INVALID_TOKEN(400, "잘못된 토큰입니다"),

    TOKEN_EXPIRED(401, "토큰이 만료되었습니다"),
    ;

    private final int status;
    private final String message;

    @Override
    public int status() { return status; }

    @Override
    public String message() { return message; }
}
