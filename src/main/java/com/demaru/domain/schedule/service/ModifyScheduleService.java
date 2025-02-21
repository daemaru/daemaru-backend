package com.demaru.domain.schedule.service;

import com.demaru.domain.archive.model.Archive;
import com.demaru.domain.archive.model.Command;
import com.demaru.domain.archive.persistence.ArchiveRepository;
import com.demaru.domain.schedule.model.Schedule;
import com.demaru.domain.schedule.persistence.ScheduleRepository;
import com.demaru.domain.schedule.presentation.dto.req.ModifyScheduleRequest;
import com.demaru.domain.user.domain.Admin;
import com.demaru.domain.user.domain.persistence.AdminRepository;
import com.demaru.global.security.SecurityService;
import java.util.Objects;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ArchiveRepository archiveRepository;
    private final AdminRepository adminRepository;
    private final SecurityService securityService;

    public void execute(ModifyScheduleRequest request, UUID scheduleId) {
        Schedule currentSchedule = scheduleRepository.findById(scheduleId).orElseThrow();
        Admin currentAdmin = adminRepository.findById(securityService.getCurrentAdminId()).orElseThrow();

        currentSchedule = scheduleRepository.save(
                Schedule.builder()
                        .id(currentSchedule.getId())
                        .title(getOrElse(request.getTitle(), currentSchedule.getTitle()))
                        .start(getOrElse(request.getStart(), currentSchedule.getStart()))
                        .end(getOrElse(request.getEnd(), currentSchedule.getEnd()))
                        .period(getOrElse(request.getPeriod(), currentSchedule.getPeriod()))
                        .location(getOrElse(request.getLocation(), currentSchedule.getLocation()))
                        .description(getOrElse(request.getDescription(), currentSchedule.getDescription()))
                        .target(getOrElse(request.getTarget(), currentSchedule.getTarget()))
                        .build()
        );

        archiveRepository.save(
                Archive.builder()
                        .admin(currentAdmin)
                        .schedule(currentSchedule)
                        .command(Command.UPDATE)
                        .context(request.toContext(securityService.getContextComma()))
                        .build()
        );
    }

    private <T> T getOrElse(T obj, T obj2) {
        return Objects.requireNonNullElse(obj, obj2);
    }
}
