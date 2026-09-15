package com.aynur.stockbot.controller;

import com.aynur.stockbot.model.AnalysisHistory;
import com.aynur.stockbot.model.AnalysisResponse;
import com.aynur.stockbot.model.MacdResult;
import com.aynur.stockbot.service.AnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Bu sinfin bir Controller olduğunu Spring-ə bildirir
@RequestMapping("api/stocks")// Bu controller-ə gələcək əsas URL yolunu təyin edir
@CrossOrigin(origins = "http://localhost:5173")
public class StockController {
 private final AnalysisService analysisService;// AnalysisService obyektini inject edirik

    public StockController(AnalysisService analysisService) {// Constructor
        this.analysisService = analysisService;
    }
    @GetMapping("/analyze/{symbol}")    // GET /api/analyze/AAPL http://localhost:8080/api/stocks/analyze/AAPL
    public AnalysisResponse analyze(
            @PathVariable String symbol // URL-dəki AAPL hissəsi
    ) {
        return analysisService.analyze(symbol);// Analizi servisə ötürürük
    }
    @GetMapping("/history")
    public List<AnalysisHistory> history() {
        return analysisService.getHistory();
    }
    @GetMapping("/history/{symbol}")
    public List<AnalysisHistory> historyBySymbol(
            @PathVariable String symbol) {

        return analysisService
                .getHistoryBySymbol(symbol);
    }

}
