package com.aynur.stockbot.controller;

import com.aynur.stockbot.model.Portfolio;
import com.aynur.stockbot.service.PortfolioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }
    @PostMapping
    public Portfolio addStock(@RequestBody Portfolio portfolio) {
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
