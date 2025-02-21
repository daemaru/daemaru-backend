package com.demaru.domain.archive.model;

import com.demaru.domain.schedule.model.Schedule;
import com.demaru.domain.user.domain.Admin;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "archive")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT")
    private long id;

    @ManyToOne(optional = false, targetEntity = Schedule.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    @ManyToOne(optional = false, targetEntity = Admin.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Command command;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String context;
}
