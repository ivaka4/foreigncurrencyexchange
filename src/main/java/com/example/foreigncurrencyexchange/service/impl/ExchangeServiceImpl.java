package com.example.foreigncurrencyexchange.service.impl;

import com.example.foreigncurrencyexchange.cache.RatesCache;
import com.example.foreigncurrencyexchange.model.ConversionRates;
import com.example.foreigncurrencyexchange.model.ConverterModel;
import com.example.foreigncurrencyexchange.model.ResponseModel;
import com.example.foreigncurrencyexchange.service.ExchangeService;
import com.example.foreigncurrencyexchange.util.Constants;
import com.example.foreigncurrencyexchange.util.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
@Component
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    private Environment env;

    @Autowired
    private RatesCache cache;

    @Override
    public ResponseModel getExchangeRate(Currency source, Currency target) {
        ResponseModel responseModel = new ResponseModel();

        String rateKey = source.getCode() + target.getCode();

        BigDecimal cachedRate = cache.getCachedRate(rateKey);
        if (null != cachedRate) {
            responseModel.setExchangeRate(cachedRate);
            System.out.println("Returing data for " + rateKey + " from Cache");
            return responseModel;
        }

        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
        uriVariables.add("access_key", env.getProperty(Constants.API_KEY));
        uriVariables.add("currencies", target.getCode());
        uriVariables.add("source", source.getCode());
        uriVariables.add("format", "1");

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(env.getProperty(Constants.API_BASE_URL)).queryParams(uriVariables).build();

        RestTemplate restTemplate = new RestTemplate();
        ConversionRates rates = restTemplate.getForObject(uriComponents.toUri(), ConversionRates.class);

        if (rates.getSuccess()) {
            String cRate = rates.getQuotes().get(rateKey);
            BigDecimal bdr = new BigDecimal(cRate);
            responseModel.setExchangeRate(bdr);
            cache.cacheRate(rateKey, bdr);
        } else {
            responseModel.setError(rates.getError());
            responseModel.setExchangeRate(BigDecimal.ZERO);
        }

        return responseModel;
    }

    @Override
    public ConverterModel getConverterModel() {
        return new ConverterModel();
    }
}
