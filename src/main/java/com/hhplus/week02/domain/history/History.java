package com.hhplus.week02.domain.history;

import com.hhplus.week02.api.history.dto.UserHistoryResponse;
import com.hhplus.week02.domain.common.entity.Timestamp;
import com.hhplus.week02.domain.lecture.Lecture;
import com.hhplus.week02.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class History extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** 강의 정보 */
    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    /** 유저 정보 */
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public UserHistoryResponse toUserHistoryRes() {
        return UserHistoryResponse.builder()
                                  .lectureId(this.lecture.getId())
                                  .lectureName(this.lecture.getName())
                                  .instructor(this.lecture.getInstructor())
                                  .build();
    }
}
