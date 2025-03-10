package com.demaru.domain.user.exception;

import com.demaru.domain.user.exception.errorCode.TeacherErrorCode;
import com.demaru.global.error.exception.BusinessException;

public class IdOrPasswordIncorrectException extends BusinessException {
    public static final IdOrPasswordIncorrectException EXCEPTION = new IdOrPasswordIncorrectException();

    private IdOrPasswordIncorrectException() { super(TeacherErrorCode.ID_OR_PASSWORD_INCORRECT); }
}