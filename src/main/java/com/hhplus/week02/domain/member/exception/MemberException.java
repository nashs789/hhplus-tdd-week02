package com.hhplus.week02.domain.member.exception;

import com.hhplus.week02.domain.member.enums.MemberExceptionMsg;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException{

    MemberExceptionMsg memberExceptionMsg;

    public MemberException(MemberExceptionMsg memberExceptionMsg) {
        super(memberExceptionMsg.getMsg());
    }
}
