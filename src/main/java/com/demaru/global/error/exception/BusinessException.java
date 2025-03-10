package com.demaru.global.error.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    public final ErrorProperty errorProperty;
}
