package com.hhplus.week02.domain.member;

import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.hhplus.week02.domain.member.MemberException.MemberExceptionMsg.*;

@Component
public class MemberValidator {

    public void validator(Long memberId) {
        if(Objects.isNull(memberId) || memberId <= 0) {
            throw new MemberException(INVALID);
        }
    }
}