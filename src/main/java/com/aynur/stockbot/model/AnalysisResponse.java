package com.aynur.stockbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnalysisResponse {
    private String symbol;
    private double rsi;

    private double macd;
    private double signalLine;

    private double ema20;
    private double ema50;
    private String signal;
//  "symbol": "AAPL","rsi": 28.5,"signal": "BUY"
}
