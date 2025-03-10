package com.demaru.global.error;

import com.demaru.global.error.dto.ErrorResponse;
import com.demaru.global.error.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(BusinessException e) {
        return ErrorResponse.toResponseEntity(e.errorProperty);
    }
}
