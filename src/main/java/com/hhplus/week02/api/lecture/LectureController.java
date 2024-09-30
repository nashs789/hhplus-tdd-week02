package com.hhplus.week02.api.lecture;

import com.hhplus.week02.api.lecture.dto.AvailableLectureResponse;
import com.hhplus.week02.application.LectureFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureFacade lectureFacade;

    @GetMapping("/available")
    public ResponseEntity<List<AvailableLectureResponse>> selectAvailableLectures() {
        return ResponseEntity.ok(lectureFacade.selectAvailableLectures());
    }

    @PostMapping("{lectureId}/register/{userId}")
    public ResponseEntity<List> registerLecture(
            @PathVariable("lectureId") final Long lectureId,
            @PathVariable("userId") final Long userId
    ) {
        // new RegisterLectureRequest(lectureId, userId)
        return null;
    }
}
