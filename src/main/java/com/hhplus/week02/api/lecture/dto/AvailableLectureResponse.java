package com.hhplus.week02.api.lecture.dto;

import com.hhplus.week02.domain.lecture.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class AvailableLectureResponse {
    private String name;
    private String instructor;
    private Lecture.LectureType type;
    private Lecture.LectureStatus status;
    private LocalDateTime regStartTime;
    private LocalDateTime regEndTime;
}
