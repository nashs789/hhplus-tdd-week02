package com.hhplus.week02.api.lecture.dto;

public record RegisterLectureRequest(Long lectureId, Long userId, long reqTime) {

    public RegisterLectureRequest(Long lectureId, Long userId) {
        this(lectureId, userId, System.currentTimeMillis());
    }
}
