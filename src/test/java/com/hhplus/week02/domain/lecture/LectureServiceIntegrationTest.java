package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureHistoryInfo;
import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.domain.member.Member;
import com.hhplus.week02.infra.lecture.LectureRepository;
import com.hhplus.week02.infra.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.hhplus.week02.domain.lecture.Lecture.LectureStatus.*;
import static com.hhplus.week02.domain.lecture.Lecture.LectureType.SPECIAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
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
    @Transactional
    public void isAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        // 신청 가능한 강의 리스트
        List<Lecture> lecturesList = List.of(
                Lecture.builder().name("Test LectureA").instructor("강사1").regStartTime(now.minusSeconds(10)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureB").instructor("강사2").regStartTime(now.minusMinutes(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureC").instructor("강사3").regStartTime(now.minusHours(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureD").instructor("강사4").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build()
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
    @Transactional
    public void isNotAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        // 유효하지 않은 신청 날짜의 강의 리스트
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
    @Transactional
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
    @Transactional
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
    @Transactional
    public void selectRegisterLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        // 신청 가능한 강의 리스트
        List<Lecture> lecturesList = List.of(
                Lecture.builder().name("Test LectureA").instructor("강사1").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureB").instructor("강사2").regStartTime(now.minusDays(10)).regEndTime(now.plusDays(10)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("Test LectureC").instructor("강사3").regStartTime(now.minusDays(100)).regEndTime(now.plusDays(100)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build()
        );

        List<Lecture> savedLectures = lectureRepository.saveAll(lecturesList);
        Member savedMember = memberRepository.save(Member.builder()
                                                         .name("Test MemberA")
                                                         .build());

        // when
        savedLectures.forEach((lecture) -> {
            lectureService.registerLecture(LectureHistory.builder()
                                                         .lecture(lecture)
                                                         .member(savedMember)
                                                         .build());
        });

        // then
        List<LectureHistoryInfo> lectureHistoryInfos = lectureService.selectHistoriesById(savedMember.getId());

        assertEquals(savedLectures.size(),
                     lectureHistoryInfos.stream()
                                        .filter(e -> e.getLecture().getName().startsWith("Test Lecture"))
                                        .count());
    }

    @Test
    @DisplayName("동시에 동일한 특강에 대해 40명이 신청했을 때, 30명만 성공")
    public void concurrencyTestForRegistration() throws InterruptedException {
        // given
        LocalDateTime now = LocalDateTime.now();
        final Long CAPACITY = 30L;
        final Long APPLICANT = 40L;
        CountDownLatch countLatch = new CountDownLatch(APPLICANT.intValue());
        ExecutorService executor = Executors.newFixedThreadPool(20);
        Lecture lecture = Lecture.builder()
                                 .name("Test LectureA")
                                 .instructor("강사1")
                                 .regStartTime(now.minusDays(1))
                                 .regEndTime(now.plusDays(1))
                                 .id(null)
                                 .capacity(CAPACITY)
                                 .registerCnt(0L)
                                 .status(REGISTER)
                                 .type(SPECIAL)
                                 .build();
        Lecture saveLecture = lectureRepository.save(lecture);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failedCount = new AtomicInteger(0);

        // when
        for(int i = 0; i < APPLICANT; i++) {
            executor.submit(() -> {
                try {
                    Member savedMember = memberRepository.save(Member.builder()
                                                                     .name("Test Member")
                                                                     .build());
                    lectureService.registerLecture(LectureHistory.builder()
                                                                 .lecture(saveLecture)
                                                                 .member(savedMember)
                                                                 .build());
                    successCount.addAndGet(1);
                } catch(LectureException ex) {
                    failedCount.addAndGet(1);
                } finally {
                    countLatch.countDown();
                }
            });
        }

        countLatch.await();

        // then
        assertEquals(CAPACITY, successCount.longValue());
        assertEquals(APPLICANT- CAPACITY, failedCount.longValue());
    }
}