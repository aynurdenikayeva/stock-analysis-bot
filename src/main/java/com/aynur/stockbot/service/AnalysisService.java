package com.aynur.stockbot.service;

import com.aynur.stockbot.mapper.BarSeriesMapper;
import com.aynur.stockbot.model.AnalysisResponse;
import com.aynur.stockbot.model.EmaResult;
import com.aynur.stockbot.model.FinnhubCandleResponse;
import com.aynur.stockbot.model.MacdResult;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;

@Service
public class AnalysisService {

    private final FinnhubService finnhubService;
    private final BarSeriesMapper mapper;
    private final RsiService rsiService;
    private final MacdService macdService;
    private final EmaService emaService;

    public AnalysisService(
            FinnhubService finnhubService,
            BarSeriesMapper mapper,
            RsiService rsiService,
            MacdService macdService,
            EmaService emaService) {

        this.finnhubService = finnhubService;
        this.mapper = mapper;
        this.rsiService = rsiService;
        this.macdService = macdService;
        this.emaService = emaService;
    }

    public AnalysisResponse analyze(String symbol) {
        // Finnhub-dan qiymət məlumatlarını alırıq
        FinnhubCandleResponse response = finnhubService.getCandles(symbol);
        // TA4J BarSeries yaradırıq
        BarSeries series = mapper.map(response, symbol);
        // RSI hesablayırıq
        double rsi = rsiService.calculate(series);
        // MACD hesablayırıq
        MacdResult macdResult = macdService.calculate(series);

        // EMA hesablayırıq
        EmaResult emaResult = emaService.calculate(series);

        // RSI siqnalları
        boolean rsiBuy = rsi < 30;
        boolean rsiSell = rsi > 70;

        // MACD siqnalları
        boolean macdBuy = macdResult.getMacd() > macdResult.getSignalLine();

        boolean macdSell = macdResult.getMacd() < macdResult.getSignalLine();

        // Trend istiqaməti
        boolean upTrend = emaResult.getEma20() > emaResult.getEma50();

        boolean downTrend = emaResult.getEma20() < emaResult.getEma50();

        String signal;
        //Sert qoyuruq
        if (rsiBuy && macdBuy && upTrend) {
            signal = "STRONG BUY";
        }
        else if (rsiSell && macdSell && downTrend) {
            signal = "STRONG SELL";
        }
        else if (macdBuy && upTrend) {
            signal = "BUY";
        }
        else if (macdSell && downTrend) {
            signal = "SELL";
        }
        else {
            signal = "HOLD";
        }

        return new AnalysisResponse(
                symbol,
                rsi,
                macdResult.getMacd(),
                macdResult.getSignalLine(),
                emaResult.getEma20(),
                emaResult.getEma50(),
                signal
        );
    }
}