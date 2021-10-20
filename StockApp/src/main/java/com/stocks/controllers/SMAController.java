package com.stocks.controllers;

import com.stocks.services.CompanyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

@Controller
public class SMAController {
    @Autowired
    CompanyStockService companyStockService;

    @PostMapping("/smaController")
    public String getCompanyInfo(@RequestParam String symbol, Model model) {
        try {
            model.addAttribute("companySymbol", symbol);
            model.addAttribute("companyName", companyStockService.findName(symbol));
            model.addAttribute("jsonfile", companyStockService.writeJSONData("singleGraph", symbol));
            return "responses/SMAOutput";
        } catch (IndexOutOfBoundsException | FileNotFoundException i) {
            return "error";
        }
    }
}
