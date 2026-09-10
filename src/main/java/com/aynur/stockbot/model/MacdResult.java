package com.aynur.stockbot.model;

import lombok.Getter;

@Getter
public class MacdResult {
    private double macd;
    private double signalLine;

    public MacdResult(double macd, double signalLine) {
        this.macd = macd;
        this.signalLine = signalLine;
    }

}
