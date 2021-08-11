package com.example.foreigncurrencyexchange.controller;

import com.example.foreigncurrencyexchange.model.ConverterModel;
import com.example.foreigncurrencyexchange.model.LoadedModel;
import com.example.foreigncurrencyexchange.model.ResponseModel;
import com.example.foreigncurrencyexchange.service.LoadDataService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
public class LoadDataController {

    @Autowired
    private LoadDataService loadDataService;

    @Autowired
    private Gson gson;

//    @RequestMapping(value = "/data", method = RequestMethod.GET)
//    public ModelAndView getData(ModelAndView modelAndView) {
//        modelAndView.setViewName("load-data");
//        modelAndView.addObject("converterModel", loadDataService.getConverterModel());
//        return modelAndView;
//    }

//    @RequestMapping(value = "/data", method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseEntity<Object> loadData(@RequestParam("date") Optional<String> date) {
//        ModelAndView modelAndView = new ModelAndView("load-data");
//        System.out.println(date);
//        if (date.isPresent()) {
//            LocalDate localDate = LocalDate.parse(date.get());
//            List<LoadedModel> loadedModel = loadDataService.getLoadedInfo(localDate);
//            modelAndView.addObject("loadedData", loadedModel);
//            return new ResponseEntity<Object>(loadedModel, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<Object>(HttpStatus.OK);
//    }

    @GetMapping("/data")
    public ModelAndView renderData(ModelAndView modelAndView,@RequestParam("date") Optional<String> date) {
        modelAndView.setViewName("load-data");
        System.out.println(date);
        if (date.isPresent()) {
            LocalDate localDate = LocalDate.parse(date.get());
            List<LoadedModel> loadedModel = loadDataService.getLoadedInfo(localDate);
            String jsonData = this.gson.toJson(loadedModel);
            modelAndView.addObject("loadedData", jsonData);
        }
        return modelAndView;
    }

}
