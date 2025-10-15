package com.baodo.timetracking.productivitytimetracking.service.commit.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommitResponseDTO {
    private UUID id;
    private LocalDate date;
    private String message;
    private int hours;
    private int minutes;
    private int duration;
    private OffsetDateTime createdAt;
}
