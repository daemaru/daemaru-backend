package com.demaru.domain.schedule.exception.errorCode;

import com.demaru.global.error.exception.ErrorProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ScheduleErrorCode implements ErrorProperty {
    SCHEDULE_NOT_FOUND(404, "존재하지 않는 일정입니다"),
    ;

    private final int status;
    private final String message;

    @Override
    public int status() { return status; }

    @Override
    public String message() { return message; }
}
