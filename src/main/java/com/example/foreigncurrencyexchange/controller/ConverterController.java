package com.example.foreigncurrencyexchange.controller;

import com.example.foreigncurrencyexchange.model.ConverterModel;
import com.example.foreigncurrencyexchange.model.ResponseModel;
import com.example.foreigncurrencyexchange.service.ConverterService;
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
public class ConverterController {
    @Autowired
    ConverterService converterService;

    @RequestMapping(value = "/converter", method = RequestMethod.GET)
    public ModelAndView converter(ModelAndView modelAndView) {
        modelAndView.setViewName("converter");
        modelAndView.addObject("converterModel", converterService.getConverterModel());
        return modelAndView;
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> doConvert(@ModelAttribute ConverterModel converterModel, Model model,
                                                   HttpServletRequest request) {

        HttpStatus status = HttpStatus.OK;

        model.addAttribute("converterModel", converterModel);

        ResponseModel rm = converterService.getConvertedValue(converterModel.getFromCurrency(), converterModel.getToCurrency(),
                converterModel.getFromAmount());

        return new ResponseEntity<ResponseModel>(rm, status);
    }
}
