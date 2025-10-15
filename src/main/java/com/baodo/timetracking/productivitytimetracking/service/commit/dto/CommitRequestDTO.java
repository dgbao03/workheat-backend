package com.baodo.timetracking.productivitytimetracking.service.commit.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommitRequestDTO {
    private LocalDate date;
    private String message;
    private int hours;
    private int minutes;
    private String time;
}
