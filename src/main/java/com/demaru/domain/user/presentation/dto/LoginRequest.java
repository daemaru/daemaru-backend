package com.demaru.domain.user.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    private String accountId;

    @NotBlank
    private String password;
}
