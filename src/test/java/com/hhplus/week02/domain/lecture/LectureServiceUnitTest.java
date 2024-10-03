package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.infra.lecture.LectureHistoryRepository;
import com.hhplus.week02.infra.lecture.LectureRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.hhplus.week02.domain.lecture.Lecture.LectureStatus.REGISTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LectureServiceUnitTest {

    private final int NO_RESULT = 0;

    @Mock
    LectureRepository lectureRepository;

    @Mock
    LectureHistoryRepository lectureHistoryRepository;

    @InjectMocks
    private LectureService lectureService;

    @Test
    @DisplayName("신청 가능한 날짜의 3개의 강의 정상 조회(유닛)")
    public void isAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        List<Lecture> lecturesList = List.of(
                new Lecture(), new Lecture(), new Lecture()
        );

        when(lectureRepository.selectAvailableLectures(now, REGISTER)).thenReturn(lecturesList);

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(lecturesList.size(), availableLectureRes.size());
        verify(lectureRepository, atLeastOnce()).selectAvailableLectures(now, REGISTER);
    }

    @Test
    @DisplayName("신청 가능한 날짜의 강의가 없음(유닛)")
    public void isNotAvailableLectures() {
        // given
        LocalDateTime now = LocalDateTime.now();
        List<Lecture> lecturesList = Collections.emptyList();

        when(lectureRepository.selectAvailableLectures(now, REGISTER)).thenReturn(lecturesList);

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(NO_RESULT, availableLectureRes.size());
        verify(lectureRepository, atLeastOnce()).selectAvailableLectures(now, REGISTER);
    }

    @Test
    @DisplayName("신청한 강의 이력 조회")
    public void selectRegisterLectures() {
        // given
        List<LectureHistory> lecturesList = List.of(
                new LectureHistory(), new LectureHistory(), new LectureHistory()
        );

        when(lectureHistoryRepository.findAllByMember_Id(anyLong())).thenReturn(lecturesList);

        // when
        List<LectureHistory> lectureHistories = lectureHistoryRepository.findAllByMember_Id(anyLong());

        // then
        assertEquals(lecturesList.size(), lectureHistories.size());
        verify(lectureHistoryRepository, atLeastOnce()).findAllByMember_Id(anyLong());
    }
}
