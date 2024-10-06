package com.hhplus.week02.infra.lecture;

import com.hhplus.week02.domain.lecture.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.hhplus.week02.domain.lecture.Lecture.LectureStatus;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query("SELECT l FROM Lecture l WHERE l.regStartTime <= :now AND l.regEndTime >= :now AND l.registerCnt < l.capacity AND l.status = :status")
    List<Lecture> selectAvailableLectures(@Param("now") LocalDateTime now, @Param("status") LectureStatus status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT l FROM Lecture l WHERE l.regStartTime <= :now AND l.regEndTime >= :now AND l.registerCnt < l.capacity AND l.status = :status")
    List<Lecture> selectAvailableLecturesWithLock(@Param("now") LocalDateTime now, @Param("status") LectureStatus status);
}
