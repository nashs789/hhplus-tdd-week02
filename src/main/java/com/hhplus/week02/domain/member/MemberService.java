package com.hhplus.week02.domain.member;

import com.hhplus.week02.infrastructure.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hhplus.week02.domain.member.MemberException.MemberExceptionMsg.NOT_EXIST;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member selectMemberById(Long userId) {
        return memberRepository.findById(userId).orElseThrow(() -> { throw new MemberException(NOT_EXIST); });
    }
}
