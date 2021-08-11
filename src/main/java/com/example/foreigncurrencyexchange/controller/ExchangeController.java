package com.example.foreigncurrencyexchange.controller;

import com.example.foreigncurrencyexchange.model.ConverterModel;
import com.example.foreigncurrencyexchange.model.ResponseModel;
import com.example.foreigncurrencyexchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExchangeController {
    @Autowired
    ExchangeService exchangeService;

    @RequestMapping(value = "/exchange", method = RequestMethod.GET)
    public ModelAndView exchangeRate(ModelAndView modelAndView) {
        modelAndView.setViewName("exchange");
        modelAndView.addObject("converterModel", exchangeService.getConverterModel());
        return modelAndView;
    }

    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> doExchange(@ModelAttribute ConverterModel converterModel, Model model,
                                                    HttpServletRequest request) {

        HttpStatus status = HttpStatus.OK;

        model.addAttribute("converterModel", converterModel);

        ResponseModel responseModel = exchangeService.getExchangeRate(converterModel.getFromCurrency(), converterModel.getToCurrency());

        return new ResponseEntity<ResponseModel>(responseModel, status);
    }
}
