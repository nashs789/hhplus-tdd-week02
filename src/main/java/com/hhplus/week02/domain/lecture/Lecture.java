package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.domain.common.entity.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Lecture extends Timestamp {

    @Id
    @GeneratedValue
    private Long id;

    /** 강의명 */
    @Column(nullable = false)
    private String name;

    /** 강사 */
    @Column(nullable = false)
    private String instructor;

    /** 정원 */
    @Column(nullable = false)
    private Long capacity;

    /** 강의 타입 */
    @Enumerated(EnumType.STRING)
    private LectureType type;

    /** 강의 상태 */
    @Enumerated(EnumType.STRING)
    private LectureStatus status;

    /** 강의 신청 시작 */
    @Column(nullable = false)
    private LocalDateTime applyStartTime;

    /** 강의 신청 종료 */
    @Column(nullable = false)
    private LocalDateTime applyEndTime;
}