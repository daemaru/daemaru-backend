package com.demaru.domain.schedule.presentation.dto.res;

import com.demaru.domain.schedule.model.Schedule;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetSchedulesResponse {
    private List<ScheduleDto> schedules;
    private Integer index;

    public static GetSchedulesResponse of(List<Schedule> schedules, Integer index) {
        return GetSchedulesResponse.builder()
                .schedules(schedules.stream().map(ScheduleDto::new).toList())
                .index(index)
                .build();
    }
}

record ScheduleDto(
        UUID id,
        String title,
        LocalDate start,
        LocalDate end,
        String period,
        String location,
        String description,
        String target
) {
    public ScheduleDto(Schedule schedule) {
        this(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getStart(),
                schedule.getEnd(),
                schedule.getPeriod(),
                schedule.getLocation(),
                schedule.getDescription(),
                schedule.getTarget()
        );
    }
}