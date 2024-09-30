package com.hhplus.week02.application;

import com.hhplus.week02.api.history.dto.UserHistoryResponse;
import com.hhplus.week02.domain.lecture.Lecture;
import com.hhplus.week02.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HistoryInfo {
    private Long id;
    private Lecture lecture;
    private Member member;

    public UserHistoryResponse toUserHistoryRes() {
        return UserHistoryResponse.builder()
                                  .lectureId(this.lecture.getId())
                                  .lectureName(this.lecture.getName())
                                  .instructor(this.lecture.getInstructor())
                                  .build();
    }
}
