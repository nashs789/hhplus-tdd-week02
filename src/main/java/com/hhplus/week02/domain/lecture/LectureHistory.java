package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureHistoryInfo;
import com.hhplus.week02.domain.common.entity.Timestamp;
import com.hhplus.week02.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(LectureHistoryId.class)
public class LectureHistory extends Timestamp {

    /** 강의 정보 */
    @Id
    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    /** 유저 정보 */
    @Id
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public LectureHistoryInfo toHistoryInfo() {
        return LectureHistoryInfo.builder()
                                 .lecture(lecture)
                                 .member(member)
                                 .build();
    }
}
