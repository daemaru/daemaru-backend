package com.demaru.global.error.dto;

import com.demaru.global.error.exception.ErrorProperty;
import org.springframework.http.ResponseEntity;

public record ErrorResponse(
        int status,
        String message
) {
    public static ErrorResponse of(ErrorProperty errorProperty) {
        return new ErrorResponse(errorProperty.status(), errorProperty.message());
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorProperty e) {
        return ResponseEntity
                .status(e.status())
                .body(new ErrorResponse(
                        e.status(),
                        e.message()
                ));
    }
}
