package com.hhplus.week02.domain.lecture;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LectureException extends RuntimeException {

    @Getter
    public enum LectureExceptionMsg {
        INVALID(HttpStatus.BAD_REQUEST, "유효하지 않은 강의 ID 입니다.")
        ;

        private final HttpStatus status;
        private final String msg;

        LectureExceptionMsg(HttpStatus status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }

    LectureExceptionMsg lectureExceptionMsg;

    public LectureException(LectureExceptionMsg lectureExceptionMsg) {
        super(lectureExceptionMsg.getMsg());
    }
}
