package com.demaru.domain.user.exception.errorCode;

import com.demaru.global.error.exception.ErrorProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TeacherErrorCode implements ErrorProperty {
    ID_OR_PASSWORD_INCORRECT(401, "아이디 또는 비밀번호가 일치하지 않습니다"),

    TEACHER_NOT_FOUND(404, "존재하지 않는 선생님 입니다"),

    CONFLICT_ACCOUNT_ID(409, "일치하는 아이디가 존재합니다")
    ;

    private final int status;
    private final String message;

    @Override
    public int status() { return status; }

    @Override
    public String message() { return message; }
}
