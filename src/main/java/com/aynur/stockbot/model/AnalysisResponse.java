package com.aynur.stockbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnalysisResponse {
    private String symbol;
    private double rsi;
    private String signal;
//  "symbol": "AAPL","rsi": 28.5,"signal": "BUY"
}
