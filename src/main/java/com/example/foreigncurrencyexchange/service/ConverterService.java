package com.example.foreigncurrencyexchange.service;

import com.example.foreigncurrencyexchange.model.ConverterModel;
import com.example.foreigncurrencyexchange.model.ResponseModel;
import com.example.foreigncurrencyexchange.util.Currency;

import java.math.BigDecimal;



public interface ConverterService {

    ResponseModel getConvertedValue(Currency source, Currency target, BigDecimal amount);
    ConverterModel getConverterModel();

}
