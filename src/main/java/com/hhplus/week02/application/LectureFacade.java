package com.hhplus.week02.application;

import com.hhplus.week02.api.history.dto.UserHistoryRes;
import com.hhplus.week02.api.lecture.dto.AvailableLectureRes;
import com.hhplus.week02.domain.history.entity.History;
import com.hhplus.week02.domain.history.service.HistoryService;
import com.hhplus.week02.domain.lecture.entity.Lecture;
import com.hhplus.week02.domain.lecture.service.LectureService;
import com.hhplus.week02.domain.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LectureFacade {

    private final LectureService lectureService;
    private final HistoryService historyService;
    private final MemberValidator memberValidator;

    public List<AvailableLectureRes> selectAvailableLectures() {
        return lectureService.selectAvailableLectures()
                             .stream()
                             .map(Lecture::toAvailableLectureRes)
                             .collect(Collectors.toUnmodifiableList());
    }

    public List<UserHistoryRes> selectHistoriesById(Long userId) {
        memberValidator.validator(userId);

        return historyService.selectHistoriesById(userId)
                             .stream()
                             .map(History::toUserHistoryRes)
                             .collect(Collectors.toUnmodifiableList());
    }
}
