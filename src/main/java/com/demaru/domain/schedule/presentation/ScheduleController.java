package com.demaru.domain.schedule.presentation;

import com.demaru.domain.schedule.presentation.dto.req.PostScheduleRequest;
import com.demaru.domain.schedule.presentation.dto.res.PostScheduleResponse;
import com.demaru.domain.schedule.service.PostScheduleService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final PostScheduleService postScheduleService;

    @PostMapping("/post")
    public PostScheduleResponse postSchedule(@Valid @RequestBody PostScheduleRequest request) {
        return postScheduleService.execute(request);
    }
}
