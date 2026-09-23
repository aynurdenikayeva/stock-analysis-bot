package com.aynur.stockbot.service;

import com.aynur.stockbot.model.Watchlist;
import com.aynur.stockbot.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {
    private final WatchlistRepository watchlistRepository;

    public WatchlistService(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }
    public Watchlist addStock(Watchlist watchlist){
        return watchlistRepository.save(watchlist);
    }
    public List<Watchlist> getWatchlist(){
        return watchlistRepository.findAll();
    }
    public void deleteStock(Long id){
        watchlistRepository.deleteById(id);
    }
}
