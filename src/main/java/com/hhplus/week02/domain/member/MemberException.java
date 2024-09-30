package com.hhplus.week02.domain.member;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberException extends RuntimeException{

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

    MemberExceptionMsg memberExceptionMsg;

    public MemberException(MemberExceptionMsg memberExceptionMsg) {
        super(memberExceptionMsg.getMsg());
    }
}
