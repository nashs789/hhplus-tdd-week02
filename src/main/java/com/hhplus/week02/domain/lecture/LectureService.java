package com.hhplus.week02.domain.lecture;

import com.hhplus.week02.infrastructure.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.hhplus.week02.domain.lecture.Lecture.LectureStatus.*;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<Lecture> selectAvailableLectures() {
        return lectureRepository.selectAvailableLectures(LocalDate.now(), REGISTER);
    }
}
