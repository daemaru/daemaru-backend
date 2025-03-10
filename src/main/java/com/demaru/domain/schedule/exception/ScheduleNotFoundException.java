package com.demaru.domain.schedule.exception;

import com.demaru.domain.schedule.exception.errorCode.ScheduleErrorCode;
import com.demaru.global.error.exception.BusinessException;

public class ScheduleNotFoundException extends BusinessException {
    public static final ScheduleNotFoundException EXCEPTION = new ScheduleNotFoundException();

    private ScheduleNotFoundException() { super(ScheduleErrorCode.SCHEDULE_NOT_FOUND); }
}
