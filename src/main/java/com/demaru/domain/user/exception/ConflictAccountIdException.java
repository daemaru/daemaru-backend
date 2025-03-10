package com.demaru.domain.user.exception;

import com.demaru.domain.user.exception.errorCode.TeacherErrorCode;
import com.demaru.global.error.exception.BusinessException;


public class ConflictAccountIdException extends BusinessException {
    public static final ConflictAccountIdException EXCEPTION = new ConflictAccountIdException();

    private ConflictAccountIdException() { super(TeacherErrorCode.CONFLICT_ACCOUNT_ID); }
}
