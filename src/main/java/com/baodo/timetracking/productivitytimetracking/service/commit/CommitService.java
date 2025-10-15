package com.baodo.timetracking.productivitytimetracking.service.commit;

import com.baodo.timetracking.productivitytimetracking.model.Commit;
import com.baodo.timetracking.productivitytimetracking.repository.CommitRepository;
import com.baodo.timetracking.productivitytimetracking.service.commit.dto.CommitRequestDTO;
import com.baodo.timetracking.productivitytimetracking.service.commit.dto.CommitResponseDTO;
import com.baodo.timetracking.productivitytimetracking.service.commit.dto.CommitSummaryResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommitService {

    private final CommitRepository commitRepository;

    public CommitService(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    public List<CommitResponseDTO> getCommitsByDate(LocalDate date) {
        List<Commit> commits = commitRepository.findByDate(date);

        return commits.stream()
                .map(c -> CommitResponseDTO.builder()
                        .id(c.getId())
                        .date(c.getDate())
                        .message(c.getMessage())
                        .hours(c.getHours())
                        .minutes(c.getMinutes())
                        .duration(c.getDuration())
                        .createdAt(c.getCreatedAt())
                        .build())
                .toList();
    }

    public CommitResponseDTO addCommit(CommitRequestDTO commitRequest) {
        int totalMinutes = commitRequest.getHours() * 60 + commitRequest.getMinutes();

        int normalizedHours = totalMinutes / 60;
        int normalizedMinutes = totalMinutes % 60;

        Commit commit = Commit.builder()
                .date(commitRequest.getDate())
                .message(commitRequest.getMessage().trim())
                .hours(normalizedHours)
                .minutes(normalizedMinutes)
                .duration(totalMinutes)
                .createdAt(OffsetDateTime.now())
                .build();

        Commit saved = commitRepository.save(commit);

        return CommitResponseDTO.builder()
                .id(saved.getId())
                .date(saved.getDate())
                .message(saved.getMessage())
                .hours(saved.getHours())
                .minutes(saved.getMinutes())
                .duration(saved.getDuration())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    public boolean deleteCommit(UUID id) {
        Optional<Commit> existingCommit = commitRepository.findById(id);
        if (existingCommit.isPresent()) {
            commitRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CommitSummaryResponseDTO> getCommitSummaryByYear(Integer year) {
        int targetYear = (year != null) ? year : Year.now().getValue();
        return commitRepository.getCommitSummaryByYear(targetYear);
    }
}
