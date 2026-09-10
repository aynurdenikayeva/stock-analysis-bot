package com.aynur.stockbot.service;

import com.aynur.stockbot.model.EmaResult;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

@Service
public class EmaService {
    public EmaResult calculate(BarSeries series) {
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);

        EMAIndicator ema20 =
                new EMAIndicator(
                        closePrice,
                        20
                );
        EMAIndicator ema50 =
                new EMAIndicator(
                        closePrice,
                        50
                );
        double ema20Value = ema20
                        .getValue(series.getEndIndex())
                        .doubleValue();
        double ema50Value = ema50
                        .getValue(series.getEndIndex())
                        .doubleValue();
        return new EmaResult(
                ema20Value,
                ema50Value
        );
    }
}