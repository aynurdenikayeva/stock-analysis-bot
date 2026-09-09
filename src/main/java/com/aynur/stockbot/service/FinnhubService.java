package com.aynur.stockbot.service;

import com.aynur.stockbot.model.FinnhubCandleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FinnhubService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${finnhub.api-key}")
    private String apiKey;

    public FinnhubCandleResponse getCandles(String symbol) {
        String url = "https://finnhub.io/api/v1/quote?symbol="
                + symbol
                + "&token="
                + apiKey;

        return restTemplate.getForObject(url, FinnhubCandleResponse.class);
    }
}