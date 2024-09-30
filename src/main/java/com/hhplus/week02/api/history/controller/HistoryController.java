package com.hhplus.week02.api.history.controller;

import com.hhplus.week02.api.history.dto.UserHistoryRes;
import com.hhplus.week02.application.LectureFacade;
import com.hhplus.week02.domain.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final LectureFacade lectureFacade;

    @GetMapping("/{userID}")
    public List<UserHistoryRes> selectHistoriesById(@PathVariable("userID") Long userId) {
        return lectureFacade.selectHistoriesById(userId);
    }
}
