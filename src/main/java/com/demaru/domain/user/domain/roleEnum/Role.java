package com.demaru.domain.user.domain.roleEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    TEACHER("교사"),
    STUDENT("학생");

    private final String description;
}