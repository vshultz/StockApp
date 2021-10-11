package com.stocks.controllers;

import com.stocks.dao.CompanyRepository;
import com.stocks.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SingleGraphController {

    @Qualifier("attributeRepository")
    @Autowired
    CompanyRepository attributeRepository;

    @Qualifier("stockRepository")
    @Autowired
    CompanyRepository stockRepository;

    @PostMapping("/home")
    public String getCompanyInfo(@RequestParam String symbol, Model model) {
        try {
            model.addAttribute("companyName", attributeRepository.findName(symbol));
            List<Stock> stockList = stockRepository.findPriceList(symbol);
            model.addAttribute("priceList", stockList);
            model.addAttribute("dateList", stockRepository.getBoundaryDates(symbol, stockList));
            return "singleGraph";
        } catch (IndexOutOfBoundsException i) {
            return "error";
        }
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

}