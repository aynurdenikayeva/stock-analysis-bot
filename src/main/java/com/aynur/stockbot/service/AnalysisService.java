package com.aynur.stockbot.service;

import com.aynur.stockbot.mapper.BarSeriesMapper;
import com.aynur.stockbot.model.AnalysisResponse;
import com.aynur.stockbot.model.FinnhubCandleResponse;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;

@Service
public class AnalysisService {
    private final FinnhubService finnhubService;
    private final BarSeriesMapper mapper;
    private final RsiService rsiService;

    public AnalysisService(FinnhubService finnhubService, BarSeriesMapper mapper, RsiService rsiService) {
        this.finnhubService = finnhubService;
        this.mapper = mapper;
        this.rsiService = rsiService;
    }

    public AnalysisResponse analyze(String symbol) {
        FinnhubCandleResponse response = finnhubService.getCandles(symbol);

        // 2. Mapper-ə symbol parametrini də ötürürük
        BarSeries series = mapper.map(response, symbol);

        double rsi = rsiService.calculate(series);

        String signal;
        if (rsi < 30) {
            signal = "BUY";
        } else if (rsi > 70) {
            signal = "SELL";
        } else {
            signal = "HOLD";
        }

        return new AnalysisResponse(symbol, rsi, signal);
    }
}