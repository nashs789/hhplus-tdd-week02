package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.application.LectureInfo;
import com.hhplus.week02.infrastructure.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.hhplus.week02.domain.lecture.Lecture.LectureStatus.*;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<LectureInfo> selectAvailableLectures() {
        return lectureRepository.selectAvailableLectures(LocalDate.now(), REGISTER)
                                .stream()
                                .map(Lecture::toLectureInfo)
                                .collect(Collectors.toUnmodifiableList());
    }
}
