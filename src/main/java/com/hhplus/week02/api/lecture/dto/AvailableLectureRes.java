package com.hhplus.week02.api.lecture.dto;

import com.hhplus.week02.domain.lecture.enums.LectureStatus;
import com.hhplus.week02.domain.lecture.enums.LectureType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class AvailableLectureRes {
    private String name;
    private String instructor;
    private LectureType type;
    private LectureStatus status;
    private LocalDateTime regStartTime;
    private LocalDateTime regEndTime;
}
