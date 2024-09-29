package com.hhplus.week02.infrastructure.history;

import com.hhplus.week02.domain.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByMember_Id(Long userId);
}
