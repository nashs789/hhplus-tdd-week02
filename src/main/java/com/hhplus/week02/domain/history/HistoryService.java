package com.hhplus.week02.domain.history;

import com.hhplus.week02.domain.lecture.Lecture;
import com.hhplus.week02.domain.member.Member;
import com.hhplus.week02.infrastructure.history.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public History selectHistoryById(Long id) {
        Optional<History> history = historyRepository.findById(id);

        if(!history.isPresent()) {
            throw new RuntimeException();
        }

        return history.get();
    }
}
