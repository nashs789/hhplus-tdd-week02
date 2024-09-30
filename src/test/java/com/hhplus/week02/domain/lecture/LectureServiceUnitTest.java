package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.infrastructure.lecture.LectureRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.hhplus.week02.domain.lecture.Lecture.LectureStatus.*;
import static com.hhplus.week02.domain.lecture.Lecture.LectureType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LectureServiceUnitTest {

    private final int NO_RESULT = 0;

    @Mock
    LectureRepository lectureRepository;

    @InjectMocks
    private LectureService lectureService;

    @Test
    @DisplayName("신청 가능한 날짜의 3개의 강의 정상 조회(유닛)")
    public void isAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        List<Lecture> lecturesList = List.of(
                Lecture.builder().name("강의1").instructor("강사1").regStartTime(now.minusDays(1)).regEndTime(now.plusDays(1)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("강의2").instructor("강사2").regStartTime(now.minusDays(10)).regEndTime(now.plusDays(10)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build(),
                Lecture.builder().name("강의3").instructor("강사3").regStartTime(now.minusDays(100)).regEndTime(now.plusDays(100)).id(null).capacity(30L).registerCnt(0L).status(REGISTER).type(SPECIAL).build()
        );

        when(lectureRepository.selectAvailableLectures(now.toLocalDate(), REGISTER)).thenReturn(lecturesList);

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(lecturesList.size(), availableLectureRes.size());
        verify(lectureRepository, atLeastOnce()).selectAvailableLectures(now.toLocalDate(), REGISTER);
    }

    @Test
    @DisplayName("신청 가능한 날짜의 강의가 없음(유닛)")
    public void isNotAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        List<Lecture> lecturesList = Collections.emptyList();

        when(lectureRepository.selectAvailableLectures(now.toLocalDate(), REGISTER)).thenReturn(lecturesList);

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(NO_RESULT, availableLectureRes.size());
        verify(lectureRepository, atLeastOnce()).selectAvailableLectures(now.toLocalDate(), REGISTER);
    }
}
