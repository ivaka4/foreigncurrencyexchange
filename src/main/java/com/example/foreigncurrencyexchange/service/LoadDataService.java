package com.example.foreigncurrencyexchange.service;

import com.example.foreigncurrencyexchange.model.ConverterModel;
import com.example.foreigncurrencyexchange.model.LoadedModel;

import java.time.LocalDate;
import java.util.List;

public interface LoadDataService {

    List<LoadedModel> getLoadedInfo (LocalDate localDate);
    ConverterModel getConverterModel();
}
