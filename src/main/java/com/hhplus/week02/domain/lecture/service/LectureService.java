package com.hhplus.week02.domain.lecture.service;

import com.hhplus.week02.api.lecture.dto.AvailableLectureRes;
import com.hhplus.week02.domain.lecture.entity.Lecture;
import com.hhplus.week02.domain.lecture.enums.LectureStatus;
import com.hhplus.week02.infrastructure.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<AvailableLectureRes> selectAvailableLectures() {
        return lectureRepository.selectAvailableLectures(LocalDate.now(), LectureStatus.REGISTER)
                                .stream()
                                .map(Lecture::toAvailableLectureRes)
                                .collect(Collectors.toUnmodifiableList());
    }
}
