package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureHistoryInfo;
import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.domain.member.Member;
import com.hhplus.week02.domain.member.MemberValidator;
import com.hhplus.week02.infra.lecture.LectureHistoryRepository;
import com.hhplus.week02.infra.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    /**
     * 현재 이용 등록이 가능한 강의 리스트
     * @return List<LectureInfo>
     */
    @Transactional(readOnly = true)
    public List<LectureInfo> selectAvailableLectures() {
        return lectureRepository.selectAvailableLectures(LocalDateTime.now(), REGISTER)
                                .stream()
                                .map(Lecture::toLectureInfo)
                                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * 유저가 신청한 강의 이력 리스트
     * @param userId 유저 아이디
     * @return List<LectureHistoryInfo>
     */
    @Transactional(readOnly = true)
    public List<LectureHistoryInfo> selectHistoriesById(final Long userId) {
        memberValidator.validator(userId);

        return lectureHistoryRepository.findAllByMember_Id(userId)
                                       .stream()
                                       .map(LectureHistory::toHistoryInfo)
                                       .collect(Collectors.toUnmodifiableList());
    }

    /**
     * 강의 등록
     * @param member 유저 정보
     * @param lecture 강의 정보
     * @return 신청 이력
     */
    @Transactional
    public LectureHistoryInfo registerLecture(Member member, Lecture lecture) {
        lectureHistoryRepository.findByMember_IdAndLecture_Id(member.getId(), lecture.getId());
                                //.orElseThrow(() -> { throw new LectureException(ALREADY_REGISTERED); });

        LectureHistory lectureHistory = LectureHistory.builder()
                                                      .lecture(lecture)
                                                      .member(member)
                                                      .build();
        Lecture registerLecture = lectureRepository.selectAvailableLecturesWithLock(LocalDateTime.now(), REGISTER)
                                                   .stream()
                                                   .filter(e -> e.getId().equals(lecture.getId()))
                                                   .findFirst()
                                                   .orElseThrow(() -> { throw new LectureException(NOT_AVAILABLE); });

        lectureRepository.save(registerLecture.increaseRegisterCount());

        return lectureHistoryRepository.save(lectureHistory).toHistoryInfo();
    }

    /**
     * 강의 조회
     * @param lectureId 강의 아이디
     * @return
     */
    @Transactional(readOnly = true)
    public Lecture selectLectureByUserId(Long lectureId) {
        return lectureRepository.findById(lectureId).orElseThrow(() -> { throw new LectureException(NOT_EXIST); });
    }
}
