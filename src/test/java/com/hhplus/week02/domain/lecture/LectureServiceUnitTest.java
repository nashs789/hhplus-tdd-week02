package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.domain.member.Member;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LectureServiceUnitTest {

    private final int NO_RESULT = 0;

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private LectureHistoryRepository lectureHistoryRepository;

    @InjectMocks
    private LectureService lectureService;

    @Test
    @DisplayName("신청 가능한 날짜의 3개의 강의 정상 조회")
    public void isAvailableLectures() {
        // given
        List<Lecture> lecturesList = List.of(
                new Lecture(), new Lecture(), new Lecture()
        );

        when(lectureRepository.selectAvailableLectures(any(LocalDateTime.class), eq(REGISTER))).thenReturn(lecturesList);

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(lecturesList.size(), availableLectureRes.size());
        verify(lectureRepository, atLeastOnce()).selectAvailableLectures(any(LocalDateTime.class), eq(REGISTER));
    }

    @Test
    @DisplayName("신청 가능한 날짜의 강의가 없음")
    public void isNotAvailableLectures() {
        // given
        when(lectureRepository.selectAvailableLectures(any(LocalDateTime.class), eq(REGISTER))).thenReturn(Collections.emptyList());

        // when
        List<LectureInfo> availableLectureRes = lectureService.selectAvailableLectures();

        // then
        assertEquals(NO_RESULT, availableLectureRes.size());
        verify(lectureRepository, atLeastOnce()).selectAvailableLectures(any(LocalDateTime.class), eq(REGISTER));
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

    @Test
    @DisplayName("동일한 유저 정보로 같은 특강을 5번 친성시, 1번만 성공")
    public void sameMemberCanRegisterOneLectureOnce() {
        // given
        Member member = Member.builder().id(1L).build();
        Lecture lecture = Lecture.builder().id(1L).build();
        when(lectureHistoryRepository.findByMember_IdAndLecture_Id(member.getId(), lecture.getId())).thenReturn(new LectureHistory());

        // when && then
        assertThrows(LectureException.class, () -> {
            lectureService.registerLecture(member, lecture);
        });
    }
}