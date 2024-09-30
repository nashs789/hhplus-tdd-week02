package com.hhplus.week02.api.history.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserHistoryRes {
    private Long lectureId;
    private String lectureName;
    private String instructor;
}
