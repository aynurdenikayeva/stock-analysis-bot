package com.aynur.stockbot.service;

import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

@Service
public class RsiService {
    public double calculate(BarSeries series){

        ClosePriceIndicator closePrice =
                new ClosePriceIndicator(series); // Bağlanış qiymətlərini götürür
        RSIIndicator rsi =
                new RSIIndicator(
                        closePrice,
                        14
                );// 14 günlük RSI

        return rsi.getValue(
                series.getEndIndex()
        ).doubleValue();   // Son RSI dəyəri
    }
}
