package com.aynur.stockbot.service;

import com.aynur.stockbot.model.MacdResult;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

@Service
public class MacdService {

    public MacdResult calculate(
            BarSeries series) {

        ClosePriceIndicator closePrice =
                new ClosePriceIndicator(series);

        MACDIndicator macdIndicator =
                new MACDIndicator(
                        closePrice,
                        12,
                        26
                );

        EMAIndicator signalIndicator =
                new EMAIndicator(
                        macdIndicator,
                        9
                );

        double macd =
                macdIndicator
                        .getValue(series.getEndIndex())
                        .doubleValue();

        double signalLine =
                signalIndicator
                        .getValue(series.getEndIndex())
                        .doubleValue();

        return new MacdResult(
                macd,
                signalLine
        );
    }
}