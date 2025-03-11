package com.demaru.domain.schedule.service;

import com.demaru.domain.archive.model.Archive;
import com.demaru.domain.archive.model.Command;
import com.demaru.domain.archive.persistence.ArchiveRepository;
import com.demaru.domain.schedule.model.Schedule;
import com.demaru.domain.schedule.persistence.ScheduleRepository;
import com.demaru.domain.schedule.presentation.dto.req.PostScheduleRequest;
import com.demaru.domain.schedule.presentation.dto.res.PostScheduleResponse;
import com.demaru.domain.user.domain.Admin;
import com.demaru.domain.user.domain.persistence.AdminRepository;
import com.demaru.domain.user.exception.TeacherNotFoundException;
import com.demaru.global.security.SecurityService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ArchiveRepository archiveRepository;
    private final SecurityService securityService;
    private final AdminRepository adminRepository;

    public PostScheduleResponse execute(PostScheduleRequest request) {
        Schedule currentSchedule = scheduleRepository.save(
                Schedule.builder()
                    .title(request.getTitle())
                    .start(request.getStart())
                    .end(request.getEnd())
                    .period(request.getPeriod())
                    .location(request.getLocation())
                    .description(request.getDescription())
                    .target(request.getTarget())
                    .isDeleted(false)
                    .build()
        );

        Admin currentAdmin = adminRepository.findById(securityService.getCurrentAdminId())
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        Archive archive = Archive.builder()
                .admin(currentAdmin)
                .schedule(currentSchedule)
                .command(Command.CREATE)
                .context(request.toContext(securityService.getContextComma()))
                .build();

        archiveRepository.save(archive);

        return PostScheduleResponse.builder()
                .id(currentSchedule.getId())
                .build();
    }
}
