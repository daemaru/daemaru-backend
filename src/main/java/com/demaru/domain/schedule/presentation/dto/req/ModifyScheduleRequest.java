package com.demaru.domain.schedule.presentation.dto.req;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class ModifyScheduleRequest {
    @Nullable private final String title;
    @Nullable private final LocalDate start;
    @Nullable private final LocalDate end;
    @Nullable private final String period;
    @Nullable private final String location;
    @Nullable private final String description;
    @Nullable private final String target;

    public String toContext(String comma) {
        Field[] fields = this.getClass().getDeclaredFields();

        return String.join(comma, Arrays.stream(fields).filter(f -> {
            try {
                return f.get(this) != null;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).map(f -> {
            try {
                return f.getName() + ":" + f.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toList());
    }
}
