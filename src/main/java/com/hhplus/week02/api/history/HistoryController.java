package com.hhplus.week02.api.history;

import com.hhplus.week02.domain.history.History;
import com.hhplus.week02.domain.history.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    // TODO - Response 타입 고민좀 해보기
    @GetMapping("/{id}")
    public History selectHistoryById(@PathVariable("id") Long id) {
        return historyService.selectHistoryById(id);
    }
}
