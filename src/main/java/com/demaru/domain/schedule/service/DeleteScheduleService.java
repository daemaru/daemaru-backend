package com.demaru.domain.schedule.service;

import com.demaru.domain.archive.model.Archive;
import com.demaru.domain.archive.model.Command;
import com.demaru.domain.archive.persistence.ArchiveRepository;
import com.demaru.domain.schedule.model.Schedule;
import com.demaru.domain.schedule.persistence.ScheduleRepository;
import com.demaru.domain.user.domain.Admin;
import com.demaru.domain.user.domain.persistence.AdminRepository;
import com.demaru.global.security.SecurityService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ArchiveRepository archiveRepository;
    private final AdminRepository adminRepository;
    private final SecurityService securityService;

    public void execute(UUID scheduleId) {
        Schedule currentSchedule = scheduleRepository.findById(scheduleId).orElseThrow(); // 404-Schedule-Not-Found
        Admin currentAdmin = adminRepository.findById(securityService.getCurrentAdminId()).orElseThrow(); // 404-Teacher-Not-Found

        currentSchedule = scheduleRepository.save(
                Schedule.builder()
                        .id(currentSchedule.getId())
                        .title(currentSchedule.getTitle())
                        .start(currentSchedule.getStart())
                        .end(currentSchedule.getEnd())
                        .period(currentSchedule.getPeriod())
                        .location(currentSchedule.getLocation())
                        .description(currentSchedule.getDescription())
                        .target(currentSchedule.getTarget())
                        .isDeleted(true)
                        .build()
        );

        archiveRepository.save(
                Archive.builder()
                        .admin(currentAdmin)
                        .schedule(currentSchedule)
                        .command(Command.DELETE)
                        .build()
        );
    }
}
