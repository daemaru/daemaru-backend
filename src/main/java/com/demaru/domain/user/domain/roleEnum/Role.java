package com.demaru.domain.user.domain.roleEnum;

import lombok.Getter;

public enum Role {
    TEACHER("교사"),
    STUDENT("학생");

    @Getter
    private final String description;

    Role(String description) {
        this.description = description;
    }

    public static Role from(String role) {
        return Role.valueOf(role.toUpperCase());
    }

    public String getKey() {
        return name();
    }
}