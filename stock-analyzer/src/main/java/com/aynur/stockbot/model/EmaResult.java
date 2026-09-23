package com.aynur.stockbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmaResult {
    private final double ema20;
    private final double ema50;
}
