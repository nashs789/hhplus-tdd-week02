package com.hhplus.week02.application;

import com.hhplus.week02.api.lecture.dto.AvailableLectureResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

import static com.hhplus.week02.domain.lecture.Lecture.*;

@Getter
@ToString
@Builder
public class LectureInfo {
    private Long id;
    private String name;
    private String instructor;
    private Long registerCnt;
    private Long capacity;
    private LectureType type;
    private LectureStatus status;
    private LocalDateTime regStartTime;
    private LocalDateTime regEndTime;

    public AvailableLectureResponse toAvailableLectureRes() {
        return AvailableLectureResponse.builder()
                                       .name(this.name)
                                       .instructor(this.instructor)
                                       .type(this.type)
                                       .status(this.status)
                                       .regStartTime(this.regStartTime)
                                       .regEndTime(this.regEndTime)
                                       .build();
    }
}
