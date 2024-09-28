package com.hhplus.week02.infrastructure.lecture;

import com.hhplus.week02.domain.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
