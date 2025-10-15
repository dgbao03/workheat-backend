package com.baodo.timetracking.productivitytimetracking.repository;

import com.baodo.timetracking.productivitytimetracking.model.Commit;
import com.baodo.timetracking.productivitytimetracking.service.commit.dto.CommitSummaryResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CommitRepository extends JpaRepository<Commit, UUID> {
    List<Commit> findByDate(LocalDate date);

    @Query("SELECT new com.baodo.timetracking.productivitytimetracking.service.commit.dto.CommitSummaryResponseDTO(c.date, SUM(c.duration)) " +
            "FROM Commit c " +
            "WHERE YEAR(c.date) = :year " +
            "GROUP BY c.date " +
            "ORDER BY c.date ASC")
    List<CommitSummaryResponseDTO> getCommitSummaryByYear(int year);
}