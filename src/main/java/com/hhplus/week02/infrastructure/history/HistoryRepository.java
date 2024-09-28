package com.hhplus.week02.infrastructure.history;

import com.hhplus.week02.domain.history.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
