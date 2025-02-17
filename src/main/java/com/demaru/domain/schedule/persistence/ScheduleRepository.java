package com.demaru.domain.schedule.persistence;

import com.demaru.domain.schedule.model.Schedule;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
