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
public class LectureHistory extends Timestamp {

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

    public LectureHistoryInfo toHistoryInfo() {
        return LectureHistoryInfo.builder()
                                 .id(id)
                                 .lecture(lecture)
                                 .member(member)
                                 .build();
    }
}
