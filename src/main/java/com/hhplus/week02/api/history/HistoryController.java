package com.hhplus.week02.api.history;

import com.hhplus.week02.api.history.dto.UserHistoryResponse;
import com.hhplus.week02.application.HistoryInfo;
import com.hhplus.week02.application.LectureFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final LectureFacade lectureFacade;

    @GetMapping("/{userID}")
    public List<UserHistoryResponse> selectHistoriesById(@PathVariable("userID") Long userId) {
        return lectureFacade.selectHistoriesById(userId)
                            .stream()
                            .map(HistoryInfo::toUserHistoryRes)
                            .collect(Collectors.toUnmodifiableList());
    }
}
