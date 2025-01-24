package com.demaru.domain.archive.model;

import com.demaru.domain.user.domain.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, columnDefinition = "LONG")
    private long id;

//    TODO : 1대다 연결
//    @ManyToOne(optional = false, targetEntity = Schedule.class)
//    @JoinColumn(name = "id", referencedColumnName = "schedule_id")
//    private Schedule schedule;

    @ManyToOne(optional = false, targetEntity = User.class)
    @JoinColumn(name = "id", referencedColumnName = "teacher_id")
    private User teacher;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Option option;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String command;
}
