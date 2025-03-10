package com.demaru.global.error;

import com.demaru.global.error.exception.ErrorProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorProperty {
    BAD_REQUEST(400, "입력값이 잘못되었습니다"),

    METHOD_NOT_ALLOWED(405, "잘못된 요청 메소드입니다"),

    INTERNAL_SERVER_ERROR(500, "서버에 에러가 발생하였습니다")
    ;

    private final Integer status;
    private final String message;

    @Override
    public int status() { return status; }

    @Override
    public String message() { return message; }
}
