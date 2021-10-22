package com.stocks.controllers;

import com.stocks.services.CompanyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

@Controller
public class LookupController {
    @Autowired
    CompanyStockService companyStockService;

    @PostMapping("/nameLookupController")
    public String getCompanyInfo(@RequestParam String name, Model model) {
        String symbol = companyStockService.findSymbol(name);
        model.addAttribute("companySymbol", symbol);
        // return the full company name listed within the database
        model.addAttribute("companyName", companyStockService.findName(symbol));
        return "responses/lookupOutput";
    }
}
