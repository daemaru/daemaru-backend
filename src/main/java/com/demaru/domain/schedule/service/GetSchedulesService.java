package com.demaru.domain.schedule.service;

import com.demaru.domain.archive.persistence.ArchiveRepository;
import com.demaru.domain.schedule.model.Schedule;
import com.demaru.domain.schedule.persistence.ScheduleRepository;
import com.demaru.domain.schedule.presentation.dto.res.GetSchedulesResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetSchedulesService {
    private final ScheduleRepository scheduleRepository;
    private final ArchiveRepository archiveRepository;

    public GetSchedulesResponse execute() {
        List<Schedule> schedules = scheduleRepository.findAllByIsDeletedOrderByStart(false);
        Integer index = archiveRepository.findAll().size();

        return GetSchedulesResponse.of(schedules, index);
    }
}
