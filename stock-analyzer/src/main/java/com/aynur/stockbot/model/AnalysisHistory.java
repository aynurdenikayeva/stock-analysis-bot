package com.aynur.stockbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="analysis_history")
@Getter
@Setter
@NoArgsConstructor
public class AnalysisHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private double rsi;
    private double macd;
    private double signalLine;
    private double ema20;
    private double ema50;
    private String signal;
    private LocalDateTime createdAt;
}
