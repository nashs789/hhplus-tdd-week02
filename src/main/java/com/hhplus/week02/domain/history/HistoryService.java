package com.hhplus.week02.domain.history;

import com.hhplus.week02.api.lecture.dto.RegisterLectureRequest;
import com.hhplus.week02.domain.member.MemberValidator;
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

    public List<History> selectHistoriesById(final Long userId) {
        memberValidator.validator(userId);

        return historyRepository.findAllByMember_Id(userId);
    }

    public void registerLecture(final RegisterLectureRequest registerLectureRequest) {

    }
}
