package com.hhplus.week02.api.lecture.controller;

import com.hhplus.week02.api.lecture.dto.AvailableLectureRes;
import com.hhplus.week02.domain.lecture.service.LectureService;
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

    // TODO - 지금 구조에서 Facade 가 필요 할까? (일단 Service)
    private final LectureService lectureService;

    @GetMapping("/available")
    public ResponseEntity<List<AvailableLectureRes>> selectAvailableLectures() {
        return ResponseEntity.ok(lectureService.selectAvailableLectures());
    }
}
