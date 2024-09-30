package com.hhplus.week02.api.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LectureHistoryResponse {
    private Long lectureId;
    private String lectureName;
    private String instructor;
}
