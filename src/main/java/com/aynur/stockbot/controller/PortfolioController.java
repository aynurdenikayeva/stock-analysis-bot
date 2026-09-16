package com.aynur.stockbot.controller;

import com.aynur.stockbot.model.Portfolio;
import com.aynur.stockbot.model.FinnhubCandleResponse;
import com.aynur.stockbot.service.PortfolioService;
import com.aynur.stockbot.service.FinnhubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    private final PortfolioService portfolioService;
    private final FinnhubService finnhubService;
    public PortfolioController(PortfolioService portfolioService, FinnhubService finnhubService) {
        this.portfolioService = portfolioService;
        this.finnhubService = finnhubService;
    }
    @PostMapping
    public Portfolio addStock(@RequestBody Portfolio portfolio) {
        String ticker = portfolio.getSymbol().toUpperCase();
        // 1. Sizin servis vasitəsilə Finnhub API-dən məlumatı çəkirik
        FinnhubCandleResponse response = finnhubService.getCandles(ticker);
        // 2. Cavabın boş olmadığını və qiymətin gəldiyini yoxlayırıq
        if (response != null && response.getC() != 0) {
            // 'c' sahəsindən canlı bazar qiymətini götürüb buyPrice-a mənimsədirik
            portfolio.setBuyPrice(response.getC());
        } else {
            throw new RuntimeException("Səhm qiyməti tapılmadı və ya API xətası: " + ticker);
        }
        portfolio.setSymbol(ticker);
        // 3. Avtomatik qiymət təyin olunduqdan sonra bazaya yazırıq
        return portfolioService.addStock(portfolio);
    }
    @GetMapping
    public List<Portfolio> getPortfolio(){
        return portfolioService.getPortfolio();
    }
    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id){
        portfolioService.deletePortfolio(id);
    }
}
