package com.demaru.domain.schedule.presentation;

import com.demaru.domain.schedule.presentation.dto.req.ModifyScheduleRequest;
import com.demaru.domain.schedule.presentation.dto.req.PostScheduleRequest;
import com.demaru.domain.schedule.presentation.dto.res.PostScheduleResponse;
import com.demaru.domain.schedule.service.ModifyScheduleService;
import com.demaru.domain.schedule.service.PostScheduleService;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final PostScheduleService postScheduleService;
    private final ModifyScheduleService modifyScheduleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public PostScheduleResponse postSchedule(@Valid @RequestBody PostScheduleRequest request) {
        return postScheduleService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{schedule_id}/patch")
    public void modifySchedule(@Valid @RequestBody ModifyScheduleRequest request, @PathVariable UUID schedule_id) {
        modifyScheduleService.execute(request, schedule_id);
    }
}
