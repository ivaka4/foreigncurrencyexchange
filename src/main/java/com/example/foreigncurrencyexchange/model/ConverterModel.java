package com.example.foreigncurrencyexchange.model;

import com.example.foreigncurrencyexchange.util.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class ConverterModel {

    private Currency fromCurrency;

    private Currency toCurrency;

    private BigDecimal fromAmount;

    private BigDecimal toAmount;

    private BigDecimal exchangeRate;

    private String date;


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

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    public List<Currency> getSupportedCurrencies() {
        return Arrays.asList(Currency.values());
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}