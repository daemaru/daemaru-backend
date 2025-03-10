package com.demaru.domain.user.exception;

import com.demaru.domain.user.exception.errorCode.TeacherErrorCode;
import com.demaru.global.error.exception.BusinessException;

public class TeacherNotFoundException extends BusinessException {
    public static final TeacherNotFoundException EXCEPTION = new TeacherNotFoundException();

    private TeacherNotFoundException() { super(TeacherErrorCode.TEACHER_NOT_FOUND); }
}