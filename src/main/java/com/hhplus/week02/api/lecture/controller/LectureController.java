package com.hhplus.week02.api.lecture.controller;

import com.hhplus.week02.api.lecture.dto.AvailableLectureRes;
import com.hhplus.week02.application.LectureFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureFacade lectureFacade;

    @GetMapping("/available")
    public ResponseEntity<List<AvailableLectureRes>> selectAvailableLectures() {
        return ResponseEntity.ok(lectureFacade.selectAvailableLectures());
    }
}
