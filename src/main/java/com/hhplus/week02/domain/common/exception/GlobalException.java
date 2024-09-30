package com.hhplus.week02.domain.common.exception;

import com.hhplus.week02.domain.lecture.LectureException;
import com.hhplus.week02.domain.member.MemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.status(500)
                             .body(new RuntimeException("에러가 발생했습니다."));
    }

    @ExceptionHandler(value = MemberException.class)
    public ResponseEntity handleException(MemberException e) {
        return ResponseEntity.status(e.getMemberExceptionMsg().getStatus())
                             .body(new RuntimeException(e.getMemberExceptionMsg().getMsg()));
    }

    @ExceptionHandler(value = LectureException.class)
    public ResponseEntity handleException(LectureException e) {
        return ResponseEntity.status(e.getLectureExceptionMsg().getStatus())
                             .body(new RuntimeException(e.getLectureExceptionMsg().getMsg()));
    }
}
