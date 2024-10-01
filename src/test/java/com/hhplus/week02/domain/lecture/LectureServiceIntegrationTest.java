package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureHistoryInfo;
import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.domain.member.Member;
import com.hhplus.week02.infrastructure.lecture.LectureRepository;
import com.hhplus.week02.infrastructure.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.hhplus.week02.domain.lecture.Lecture.LectureStatus.*;
import static com.hhplus.week02.domain.lecture.Lecture.LectureType.SPECIAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class LectureServiceIntegrationTest {

    private final int NO_RESULT = 0;
    private final Logger log = LoggerFactory.getLogger(LectureServiceIntegrationTest.class);

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("신청 가능한 날짜의 3개의 강의 정상 조회(통합)")
    public void isAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        List<Lecture> lecturesList = List.of(
                Lecture.builder().name("Test LectureA").instructor("강사1").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureB").instructor("강사2").regStartTime(now.minusDays(10)).regEndTime(now.plusDays(10)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureC").instructor("강사3").regStartTime(now.minusDays(100)).regEndTime(now.plusDays(100)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build()
        );

        lectureRepository.saveAll(lecturesList);

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(lecturesList.size(), availableLectureRes.stream()
                                                             .filter(e -> e.getName().startsWith("Test Lecture"))
                                                             .count());
    }

    @Test
    @DisplayName("신청 가능한 날짜의 강의가 없음(통합)")
    public void isNotAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        List<Lecture> lecturesList = List.of(
                Lecture.builder().name("Test LectureA").instructor("강사1").regStartTime(now.minusDays(10)).regEndTime(now.minusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureB").instructor("강사2").regStartTime(now.plusDays(10)).regEndTime(now.plusDays(20)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureC").instructor("강사3").regStartTime(now.plusDays(1)).regEndTime(now.plusDays(100)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build()
        );

        lectureRepository.saveAll(lecturesList);

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(NO_RESULT, availableLectureRes.stream()
                                                   .filter(e -> e.getName().startsWith("Test Lecture"))
                                                   .count());
    }

    @Test
    @DisplayName("정원이 가득 찬 강의는 조회 불가능")
    public void capacityIsFull() {
        // given
        LocalDateTime now = LocalDateTime.now();
        // 정원이 30 가득찬 강의 리스트
        List<Lecture> lecturesList = List.of(
                Lecture.builder().name("Test LectureA").instructor("강사1").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(30L).status(FINISHED).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureB").instructor("강사2").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(30L).status(FINISHED).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureC").instructor("강사3").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(30L).status(FINISHED).type(SPECIAL).build()
        );

        lectureRepository.saveAll(lecturesList);

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(NO_RESULT, availableLectureRes.stream()
                                                   .filter(e -> e.getName().startsWith("Test Lecture"))
                                                   .count());
    }

    @Test
    @DisplayName("신청 불가능 상태 강의 조회 불가능")
    public void onlyAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        // status 상태가 REGISTER 가 아님, 정원이 가득 참
        List<Lecture> lecturesList = List.of(
                Lecture.builder().name("Test LectureA").instructor("강사1").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(PENDING).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureB").instructor("강사2").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(CANCELED).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureC").instructor("강사3").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(30L).status(FINISHED).type(SPECIAL).build()
        );

        lectureRepository.saveAll(lecturesList);

        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(NO_RESULT, availableLectureRes.stream()
                                                   .filter(e -> e.getName().startsWith("Test Lecture"))
                                                   .count());
    }

    @Test
    @DisplayName("신청한 강의 이력 조회")
    public void selectRegisterLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        // 신청 가능한 강의 리스트
        List<Lecture> lecturesList = List.of(
                Lecture.builder().name("Test LectureA").instructor("강사1").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureB").instructor("강사2").regStartTime(now.minusDays(10)).regEndTime(now.plusDays(10)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureC").instructor("강사3").regStartTime(now.minusDays(100)).regEndTime(now.plusDays(100)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build()
        );

        lectureRepository.saveAll(lecturesList);

        Member member = Member.builder()
                              .name("Test MemberA")
                              .build();
        Member savedMember = memberRepository.save(member);

        // when
        lecturesList.forEach((lecture) -> {
            lectureService.registerLecture(LectureHistory.builder()
                                                         .lecture(lecture)
                                                         .member(savedMember)
                                                         .build());
        });

        // then
        List<LectureHistoryInfo> lectureHistoryInfos = lectureService.selectHistoriesById(savedMember.getId());

        assertEquals(lecturesList.size(), lectureHistoryInfos.size());
        assertEquals(lecturesList.size(),
                     lectureHistoryInfos.stream()
                                        .filter(e -> e.getLecture().getName().startsWith("Test Lecture"))
                                        .count());
    }
}