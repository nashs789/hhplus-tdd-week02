package com.hhplus.week02.application;

import com.hhplus.week02.api.lecture.dto.LectureHistoryResponse;
import com.hhplus.week02.domain.lecture.Lecture;
import com.hhplus.week02.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class LectureHistoryInfo {
    private Long id;
    private Lecture lecture;
    private Member member;

    public LectureHistoryResponse toUserHistoryRes() {
        return LectureHistoryResponse.builder()
                                     .lectureId(this.lecture.getId())
                                     .lectureName(this.lecture.getName())
                                     .instructor(this.lecture.getInstructor())
                                     .build();
    }
}
