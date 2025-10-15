package com.baodo.timetracking.productivitytimetracking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "commits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 255)
    private String message;

    @Column(nullable = false)
    private int hours;

    @Column(nullable = false)
    private int minutes;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt =  OffsetDateTime.now();
}
