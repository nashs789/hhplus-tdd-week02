package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.domain.common.entity.Timestamp;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lecture extends Timestamp {

    public enum LectureStatus {
        REGISTER, PENDING, CANCELED, FINISHED
    }

    public enum LectureType {
        COMMON, SPECIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** 강의명 */
    @Column(nullable = false)
    private String name;

    /** 강사 */
    @Column(nullable = false)
    private String instructor;

    /** 현재 신청자 */
    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long registerCnt;

    /** 정원 */
    @Column(nullable = false, columnDefinition = "bigint default 30")
    private Long capacity;

    /** 강의 타입 */
    @Enumerated(EnumType.STRING)
    private LectureType type;

    /** 강의 상태 */
    @Enumerated(EnumType.STRING)
    private LectureStatus status;

    /** 강의 신청 시작 */
    @Column(nullable = false)
    private LocalDateTime regStartTime;

    /** 강의 신청 종료 */
    @Column(nullable = false)
    private LocalDateTime regEndTime;

    public LectureInfo toLectureInfo() {
        return LectureInfo.builder()
                          .id(id)
                          .name(name)
                          .instructor(instructor)
                          .capacity(capacity)
                          .type(type)
                          .status(status)
                          .regStartTime(regStartTime)
                          .regEndTime(regEndTime)
                          .build();
    }
}