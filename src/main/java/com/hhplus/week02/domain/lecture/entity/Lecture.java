package com.hhplus.week02.domain.lecture.entity;

import com.hhplus.week02.api.lecture.dto.AvailableLectureRes;
import com.hhplus.week02.domain.common.entity.Timestamp;
import com.hhplus.week02.domain.lecture.enums.LectureStatus;
import com.hhplus.week02.domain.lecture.enums.LectureType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lecture extends Timestamp {

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
    @Column(nullable = false, columnDefinition = "bigint default 0")
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

    public AvailableLectureRes toAvailableLectureRes() {
        return AvailableLectureRes.builder()
                                  .name(this.name)
                                  .instructor(this.instructor)
                                  .type(this.type)
                                  .status(this.status)
                                  .regStartTime(this.regStartTime)
                                  .regEndTime(this.regEndTime)
                                  .build();
    }
}