package com.example.foreigncurrencyexchange.model;

import java.math.BigDecimal;

public class ResponseModel {

    private BigDecimal convertedValue;
    private BigDecimal exchangeRate;
    private Error error;
    /**
     * @return the convertedValue
     */
    public BigDecimal getConvertedValue() {
        return convertedValue;
    }
    /**
     * @param convertedValue the convertedValue to set
     */
    public void setConvertedValue(BigDecimal convertedValue) {
        this.convertedValue = convertedValue;
    }
    /**
     * @return the error
     */
    public Error getError() {
        return error;
    }
    /**
     * @param error the error to set
     */
    public void setError(Error error) {
        this.error = error;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
