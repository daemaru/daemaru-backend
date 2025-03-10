package com.demaru.global.error;

import com.demaru.global.error.exception.BusinessException;

public class InternalServerErrorException extends BusinessException {
    public static final InternalServerErrorException EXCEPTION = new InternalServerErrorException();

    private InternalServerErrorException() { super(GlobalErrorCode.INTERNAL_SERVER_ERROR); }
}
