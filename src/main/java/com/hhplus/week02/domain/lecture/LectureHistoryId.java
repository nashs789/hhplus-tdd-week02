package com.hhplus.week02.domain.lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureHistoryId {

    private Long lecture;
    private Long member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LectureHistoryId that = (LectureHistoryId) o;

        if (!lecture.equals(that.lecture)) return false;
        return member.equals(that.member);
    }

    @Override
    public int hashCode() {
        int result = lecture.hashCode();
        result = 31 * result + member.hashCode();
        return result;
    }
}
