package com.example.foreigncurrencyexchange.model;

import com.example.foreigncurrencyexchange.util.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoadedModel {

    private Currency fromCurrency;

    private Currency toCurrency;

    private BigDecimal exchangeRate;

    private LocalDate date;

    public LoadedModel() {
    }

    public LoadedModel(Currency fromCurrency, Currency toCurrency, BigDecimal exchangeRate, LocalDate date) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.date = date;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
