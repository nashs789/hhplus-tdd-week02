package com.hhplus.week02.domain.member.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberExceptionMsg {
    INVALID(HttpStatus.BAD_REQUEST, "유효하지 않은 유저 ID 입니다.")
    ;

    private final HttpStatus status;
    private final String msg;

    MemberExceptionMsg(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
