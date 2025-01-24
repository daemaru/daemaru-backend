package com.demaru.domain.schedule.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "start", nullable = false)
    private LocalDate start;

    @Column(name = "end", nullable = false)
    private LocalDate end;

    @Column(name = "period", nullable = false)
    private short period;

    @Column(name = "location", nullable = false, length = 50)
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "target", nullable = false, length = 30)
    private String target;
}
