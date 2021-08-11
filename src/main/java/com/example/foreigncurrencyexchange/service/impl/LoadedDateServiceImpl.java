package com.example.foreigncurrencyexchange.service.impl;

import com.example.foreigncurrencyexchange.cache.RatesCache;
import com.example.foreigncurrencyexchange.model.ConversionRates;
import com.example.foreigncurrencyexchange.model.ConverterModel;
import com.example.foreigncurrencyexchange.model.LoadedModel;
import com.example.foreigncurrencyexchange.service.LoadDataService;
import com.example.foreigncurrencyexchange.util.Constants;
import com.example.foreigncurrencyexchange.util.Currency;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class LoadedDateServiceImpl implements LoadDataService {
    @Autowired
    private Environment env;

    @Autowired
    private RatesCache cache;
    @Override
    public List<LoadedModel> getLoadedInfo(LocalDate localDate) {
        List<LoadedModel> loadedModels = new LinkedList<>();
        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
        uriVariables.add("access_key", env.getProperty(Constants.API_KEY));
        uriVariables.add("date", localDate.toString());
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(env.getProperty(Constants.LOAD_DATA_URL)).queryParams(uriVariables).build();
        RestTemplate restTemplate = new RestTemplate();
        ConversionRates rates = restTemplate.getForObject(uriComponents.toUri(), ConversionRates.class);

        if (rates.getSuccess()) {
            for (Map.Entry<String, String> entry: rates.getQuotes().entrySet()) {
                String twoCurrencies = entry.getKey();
                String sourceCurrency = twoCurrencies.substring(0,3);
                String targetCurrency = twoCurrencies.substring(3);
                BigDecimal rate = new BigDecimal(entry.getValue());
                LoadedModel model = new LoadedModel(Currency.fromCode(sourceCurrency), Currency.fromCode(targetCurrency), rate, localDate);
                loadedModels.add(model);
            }
            System.out.println();
        } else {

        }

        return loadedModels;
    }

    @Override
    public ConverterModel getConverterModel() {
        return new ConverterModel();
    }
}
