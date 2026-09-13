package com.aynur.stockbot.repository;

import com.aynur.stockbot.model.AnalysisHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository extends JpaRepository<AnalysisHistory ,Long> {
}
