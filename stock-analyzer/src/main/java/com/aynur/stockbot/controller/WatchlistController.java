package com.aynur.stockbot.controller;

import com.aynur.stockbot.model.Watchlist;
import com.aynur.stockbot.service.WatchlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    private final WatchlistService watchlistservice;

    public WatchlistController(WatchlistService watchlistservice) {
        this.watchlistservice = watchlistservice;
    }

    @PostMapping
    public Watchlist addStock(@RequestBody Watchlist watchList){
        return watchlistservice.addStock(watchList);
    }
    @GetMapping
    public List<Watchlist> getWatchlist(){
        return watchlistservice.getWatchlist();
    }
    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id){
        watchlistservice.deleteStock(id);
    }
}
