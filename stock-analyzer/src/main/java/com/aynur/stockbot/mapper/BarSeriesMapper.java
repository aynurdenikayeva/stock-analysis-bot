package com.aynur.stockbot.mapper;

import com.aynur.stockbot.model.FinnhubCandleResponse;
import org.springframework.stereotype.Component;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;

import java.time.Duration;
import java.time.ZonedDateTime;

@Component
public class BarSeriesMapper {

    public BarSeries map(FinnhubCandleResponse response, String symbol) {
        BarSeries series = new BaseBarSeries(symbol != null ? symbol : "Stock");

        if (response != null && response.getC() != null) {
            Double basePrice = response.getC();
            int seed = symbol != null ? symbol.hashCode() : 1;

            for (int i = 15; i >= 0; i--) {
                // Hər səhm üçün fərqli dinamika yaradan psövdo-random hesablama
                double pseudoRandomFactor = Math.sin(seed + i) * 2.0;
                double simulatedPrice = Math.max(1.0, basePrice + pseudoRandomFactor);

                series.addBar(
                        new BaseBar(
                                Duration.ofDays(1),
                                ZonedDateTime.now().minusDays(i),
                                simulatedPrice,
                                simulatedPrice,
                                simulatedPrice,
                                simulatedPrice,
                                1000
                        )
                );
            }
        }

        return series;
    }
}