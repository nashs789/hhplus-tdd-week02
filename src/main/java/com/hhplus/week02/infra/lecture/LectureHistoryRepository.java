package com.hhplus.week02.infra.lecture;

import com.hhplus.week02.domain.lecture.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureHistoryRepository extends JpaRepository<LectureHistory, Long> {

    List<LectureHistory> findAllByMember_Id(Long userId);
}
