package com.hhplus.week02.api.lecture;

import com.hhplus.week02.api.lecture.dto.LectureHistoryResponse;
import com.hhplus.week02.api.lecture.dto.AvailableLectureResponse;
import com.hhplus.week02.api.lecture.dto.RegisterLectureRequest;
import com.hhplus.week02.application.LectureHistoryInfo;
import com.hhplus.week02.application.LectureFacade;
import com.hhplus.week02.application.LectureInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureFacade lectureFacade;

    @GetMapping("/available")
    public ResponseEntity<List<AvailableLectureResponse>> selectAvailableLectures() {
        List<AvailableLectureResponse> lectureInfos = lectureFacade.selectAvailableLectures()
                                                                   .stream()
                                                                   .map(LectureInfo::toAvailableLectureRes)
                                                                   .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok(lectureInfos);
    }

    @GetMapping("/{userID}")
    public List<LectureHistoryResponse> selectHistoriesById(@PathVariable("userID") Long userId) {
        return lectureFacade.selectHistoriesById(userId)
                            .stream()
                            .map(LectureHistoryInfo::toUserHistoryRes)
                            .collect(Collectors.toUnmodifiableList());
    }

    @PostMapping("/register")
    public ResponseEntity<LectureHistoryResponse> registerLecture(@RequestBody RegisterLectureRequest registerLectureRequest) {
        LectureHistoryResponse userHistoryRes = lectureFacade.registerLecture(registerLectureRequest)
                                                             .toUserHistoryRes();
        return ResponseEntity.ok(userHistoryRes);
    }
}
