package com.hhplus.week02.application;

import com.hhplus.week02.api.lecture.dto.RegisterLectureRequest;
import com.hhplus.week02.domain.lecture.LectureHistory;
import com.hhplus.week02.domain.lecture.Lecture;
import com.hhplus.week02.domain.lecture.LectureService;
import com.hhplus.week02.domain.lecture.LectureValidator;
import com.hhplus.week02.domain.member.Member;
import com.hhplus.week02.domain.member.MemberService;
import com.hhplus.week02.domain.member.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureFacade {

    private final LectureService lectureService;
    private final MemberValidator memberValidator;
    private final LectureValidator lectureValidator;
    private final MemberService memberService;

    public List<LectureInfo> selectAvailableLectures() {
        return lectureService.selectAvailableLectures();
    }

    public List<LectureHistoryInfo> selectHistoriesById(final Long userId) {
        memberValidator.validator(userId);

        return lectureService.selectHistoriesById(userId);
    }

    public LectureHistoryInfo registerLecture(final RegisterLectureRequest registerLectureRequest) {
        memberValidator.validator(registerLectureRequest.userId());
        lectureValidator.validator(registerLectureRequest.lectureId());

        Lecture lecture = lectureService.selectLectureByUserId(registerLectureRequest.lectureId());
        Member member = memberService.selectMemberById(registerLectureRequest.userId());

        return lectureService.registerLecture(member, lecture);
    }
}
