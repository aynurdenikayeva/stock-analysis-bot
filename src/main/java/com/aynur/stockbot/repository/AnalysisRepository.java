package com.aynur.stockbot.repository;

import com.aynur.stockbot.model.AnalysisHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisRepository extends JpaRepository<AnalysisHistory ,Long> {
    List<AnalysisHistory> findBySymbol(String symbol);
}
