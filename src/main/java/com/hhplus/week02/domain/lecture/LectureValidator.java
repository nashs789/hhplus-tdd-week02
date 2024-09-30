package com.hhplus.week02.domain.lecture;

import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.hhplus.week02.domain.lecture.LectureException.LectureExceptionMsg.INVALID;

@Component
public class LectureValidator {

    public void validator(Long lectureId) {
        if(Objects.isNull(lectureId) || lectureId <= 0) {
            throw new LectureException(INVALID);
        }
    }
}
