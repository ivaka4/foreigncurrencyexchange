package com.example.foreigncurrencyexchange.service;

import com.example.foreigncurrencyexchange.model.ConverterModel;
import com.example.foreigncurrencyexchange.model.ResponseModel;
import com.example.foreigncurrencyexchange.util.Currency;

public interface ExchangeService {
    ResponseModel getExchangeRate(Currency source, Currency target);
    ConverterModel getConverterModel();
}
