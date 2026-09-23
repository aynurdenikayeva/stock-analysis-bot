package com.aynur.stockbot.service;

import com.aynur.stockbot.mapper.BarSeriesMapper;
import com.aynur.stockbot.model.*;
import com.aynur.stockbot.repository.AnalysisRepository;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnalysisService {

    private final FinnhubService finnhubService;
    private final BarSeriesMapper mapper;
    private final RsiService rsiService;
    private final MacdService macdService;
    private final EmaService emaService;
    private final AnalysisRepository analysisRepository;

    public AnalysisService(
            FinnhubService finnhubService,
            BarSeriesMapper mapper,
            RsiService rsiService,
            MacdService macdService,
            EmaService emaService, AnalysisRepository analysisRepository) {

        this.finnhubService = finnhubService;
        this.mapper = mapper;
        this.rsiService = rsiService;
        this.macdService = macdService;
        this.emaService = emaService;
        this.analysisRepository = analysisRepository;
    }
    public List<AnalysisHistory> getHistory() {
        return analysisRepository.findAll();
    }
    public List<AnalysisHistory> getHistoryBySymbol(String symbol) {
        return analysisRepository
                .findBySymbol(symbol);
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
        AnalysisHistory history =
                new AnalysisHistory();

        history.setSymbol(symbol);
        history.setRsi(rsi);

        history.setMacd(
                macdResult.getMacd());

        history.setSignalLine(
                macdResult.getSignalLine());

        history.setEma20(
                emaResult.getEma20());

        history.setEma50(
                emaResult.getEma50());

        history.setSignal(signal);

        history.setCreatedAt(LocalDateTime.now()
        );

        analysisRepository.save(history);         // PostgreSQL-ə yazırıq


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