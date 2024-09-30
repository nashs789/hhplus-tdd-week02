package com.hhplus.week02.application;

import com.hhplus.week02.api.history.dto.UserHistoryResponse;
import com.hhplus.week02.api.lecture.dto.AvailableLectureResponse;
import com.hhplus.week02.api.lecture.dto.RegisterLectureRequest;
import com.hhplus.week02.domain.history.History;
import com.hhplus.week02.domain.history.HistoryService;
import com.hhplus.week02.domain.lecture.Lecture;
import com.hhplus.week02.domain.lecture.LectureService;
import com.hhplus.week02.domain.member.MemberValidator;
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

    public List<AvailableLectureResponse> selectAvailableLectures() {
        return lectureService.selectAvailableLectures()
                             .stream()
                             .map(Lecture::toAvailableLectureRes)
                             .collect(Collectors.toUnmodifiableList());
    }

    public List<UserHistoryResponse> selectHistoriesById(final Long userId) {
        memberValidator.validator(userId);

        return historyService.selectHistoriesById(userId)
                             .stream()
                             .map(History::toUserHistoryRes)
                             .collect(Collectors.toUnmodifiableList());
    }

    public void registerLecture(final RegisterLectureRequest registerLectureRequest) {

    }
}
