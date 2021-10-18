package com.stocks.controllers;

import com.stocks.domain.Stock;
import com.stocks.services.CompanyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class SingleGraphController {
    @Autowired
    CompanyStockService companyStockService;

    @PostMapping("/home")
    public String getCompanyInfo(@RequestParam String symbol, Model model) {
        try {
            model.addAttribute("companyName", companyStockService.findName(symbol));
            List<Stock> stockList = companyStockService.findPriceList(symbol);
            String jsonfile = companyStockService.writeJSONData(stockList, "singleGraph");
            model.addAttribute("jsonfile", jsonfile);
            return "responses/candlestickVolume";
        } catch (IndexOutOfBoundsException | FileNotFoundException i) {
            return "error";
        }
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}