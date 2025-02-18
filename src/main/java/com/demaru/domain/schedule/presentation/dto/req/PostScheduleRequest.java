package com.demaru.domain.schedule.presentation.dto.req;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostScheduleRequest {
    @NotBlank private final String title;
    @NotNull private final LocalDate start;
    @NotNull private final LocalDate end;
    @NotBlank private final String period;
    @NotBlank private final String location;
    private final String description = "";
    @NotBlank private final String target;

    public String toContext(String comma) {
        return "title:" + title + comma
                + "start:" + start + comma
                + "end:" + end + comma
                + "period:" + period + comma
                + "location:" + location + comma
                + "description:" + description + comma
                + "target:" + target;
    }
}
