package com.baodo.timetracking.productivitytimetracking.controller;

import com.baodo.timetracking.productivitytimetracking.service.commit.dto.CommitRequestDTO;
import com.baodo.timetracking.productivitytimetracking.service.commit.dto.CommitResponseDTO;
import com.baodo.timetracking.productivitytimetracking.service.commit.CommitService;
import com.baodo.timetracking.productivitytimetracking.service.commit.dto.CommitSummaryResponseDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/commits")
@CrossOrigin(origins = "https://workheat-frontend.vercel.app")
public class CommitController {

    private final CommitService commitService;

    public CommitController(CommitService commitService) {
        this.commitService = commitService;
    }

    @PostMapping
    public ResponseEntity<CommitResponseDTO> addCommit(@RequestBody CommitRequestDTO commit) {
        return ResponseEntity.ok(commitService.addCommit(commit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommit(@PathVariable UUID id) {
        boolean deleted = commitService.deleteCommit(id);

        if (deleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CommitResponseDTO>> getCommitsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(commitService.getCommitsByDate(date));
    }

    @GetMapping("/summary")
    public ResponseEntity<List<CommitSummaryResponseDTO>> getCommitSummaryByYear(@RequestParam(required = false) Integer year) {
        List<CommitSummaryResponseDTO> result = commitService.getCommitSummaryByYear(year);
        return ResponseEntity.ok(result);
    }
}
