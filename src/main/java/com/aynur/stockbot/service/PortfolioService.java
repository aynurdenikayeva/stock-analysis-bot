package com.aynur.stockbot.service;

import com.aynur.stockbot.model.Portfolio;
import com.aynur.stockbot.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }
    public Portfolio addStock(Portfolio portfolio) {
      return portfolioRepository.save(portfolio);
    }
    public List<Portfolio> getPortfolio() {
        return portfolioRepository.findAll();
    }
    public void deletePortfolio(Long portfolioId) {
        portfolioRepository.deleteById(portfolioId);
    }
}
