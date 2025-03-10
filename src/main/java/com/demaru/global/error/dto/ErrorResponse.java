package com.demaru.global.error.dto;

import com.demaru.global.error.exception.ErrorProperty;

public record ErrorResponse(
        int status,
        String message
) {
    public static ErrorResponse of(ErrorProperty errorProperty) {
        return new ErrorResponse(errorProperty.status(), errorProperty.message());
    }
}
