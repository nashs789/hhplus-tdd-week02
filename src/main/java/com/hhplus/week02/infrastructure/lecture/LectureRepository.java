package com.hhplus.week02.infrastructure.lecture;

import com.hhplus.week02.domain.lecture.entity.Lecture;
import com.hhplus.week02.domain.lecture.enums.LectureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query("SELECT l FROM Lecture l WHERE FUNCTION('DATE', l.regStartTime) <= :now AND FUNCTION('DATE', l.regEndTime) >= :now AND l.registerCnt < 30 AND l.status = :status")
    List<Lecture> selectAvailableLectures(@Param("now") LocalDate now, @Param("status")LectureStatus status);
}
