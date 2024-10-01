package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureHistoryInfo;
import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.domain.lecture.LectureException.LectureExceptionMsg;
import com.hhplus.week02.domain.member.MemberValidator;
import com.hhplus.week02.infrastructure.lecture.LectureHistoryRepository;
import com.hhplus.week02.infrastructure.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hhplus.week02.domain.lecture.Lecture.LectureStatus.REGISTER;
import static com.hhplus.week02.domain.lecture.LectureException.LectureExceptionMsg.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureHistoryRepository lectureHistoryRepository;
    private final MemberValidator memberValidator;

    public List<LectureInfo> selectAvailableLectures() {
        return lectureRepository.selectAvailableLectures(LocalDate.now(), REGISTER)
                                .stream()
                                .map(Lecture::toLectureInfo)
                                .collect(Collectors.toUnmodifiableList());
    }

    public List<LectureHistoryInfo> selectHistoriesById(final Long userId) {
        memberValidator.validator(userId);

        return lectureHistoryRepository.findAllByMember_Id(userId)
                                       .stream()
                                       .map(LectureHistory::toHistoryInfo)
                                       .collect(Collectors.toUnmodifiableList());
    }

    public LectureHistoryInfo registerLecture(LectureHistory lectureHistory) {
        Lecture targetLecture = lectureHistory.getLecture();
        Set<Long> availableLectureIds = selectAvailableLectures().stream()
                                                                 .map(LectureInfo::getId)
                                                                 .collect(Collectors.toSet());

        if(!availableLectureIds.contains(targetLecture.getId())) {
            throw new LectureException(NOT_AVAILABLE);
        }

        return lectureHistoryRepository.save(lectureHistory)
                                       .toHistoryInfo();
    }

    public Lecture selectLectureByUserId(Long userId) {
        return lectureRepository.findById(userId).orElseThrow(() -> { throw new LectureException(NOT_EXIST); });
    }
}
