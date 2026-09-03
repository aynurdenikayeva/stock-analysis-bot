package com.aynur.stockbot.controller;

import com.aynur.stockbot.model.AnalysisResponse;
import com.aynur.stockbot.service.AnalysisService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Bu sinfin bir Controller olduğunu Spring-ə bildirir
@RequestMapping("api/stocks")// Bu controller-ə gələcək əsas URL yolunu təyin edir
public class StockController {
 private final AnalysisService analysisService;// AnalysisService obyektini inject edirik

    public StockController(AnalysisService analysisService) {// Constructor
        this.analysisService = analysisService;
    }

    // GET sorğusu üçün: http://localhost:8080/api/stocks
    public AnalysisResponse analyze(@PathVariable String symbol ){
        return AnalysisService.analyze(symbol);
}
}
