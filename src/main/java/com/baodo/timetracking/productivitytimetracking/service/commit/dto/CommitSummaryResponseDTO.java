package com.baodo.timetracking.productivitytimetracking.service.commit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CommitSummaryResponseDTO {
    private LocalDate date;
    private Long duration;


}