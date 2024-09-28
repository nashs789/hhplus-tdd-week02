package com.hhplus.week02.api.lecture;

import com.hhplus.week02.domain.lecture.Lecture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/lecture")
public class LectureController {

    @GetMapping("/{id}")
    public void selectLectureById(@PathVariable("id") Long id) {

    }

    @PostMapping("/{id}")
    public void applyLectureById(@PathVariable("id") Long id, LectureReq lectureReq) {

    }
}
