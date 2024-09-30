package com.hhplus.week02.domain.history.service;

import com.hhplus.week02.domain.history.entity.History;
import com.hhplus.week02.domain.member.validator.MemberValidator;
import com.hhplus.week02.infrastructure.history.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final MemberValidator memberValidator;

    public List<History> selectHistoriesById(Long userId) {
        memberValidator.validator(userId);

        return historyRepository.findAllByMember_Id(userId);
    }
}
