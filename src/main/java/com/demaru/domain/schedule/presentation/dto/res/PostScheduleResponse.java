package com.demaru.domain.schedule.presentation.dto.res;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostScheduleResponse {
    private UUID id;
}
