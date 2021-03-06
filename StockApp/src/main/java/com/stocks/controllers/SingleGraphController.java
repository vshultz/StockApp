package com.stocks.controllers;

import com.stocks.services.CompanyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@Controller
public class SingleGraphController {
    @Autowired
    CompanyStockService companyStockService;

    @PostMapping("/home")
    public String getCompanyInfo(@RequestParam String symbol, Model model) {
        try {
            model.addAttribute("companySymbol", symbol);
            model.addAttribute("companyName", companyStockService.findName(symbol));
            model.addAttribute("jsonfile", companyStockService.writeJSONData("singleGraph", symbol));
            return "responses/candlestickVolumeOutput";
        } catch (IndexOutOfBoundsException | FileNotFoundException i) {
            return "error";
        }
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/index")
    public String home() { return "index"; }

    @GetMapping("/ema")
    public String smaVolume() {
        return "EMA";
    }

    @GetMapping("/GUI")
    public String gui(){
        return "GUI";
    }

    @GetMapping("/lookup")
    public String lookup(){
        return "lookup";
    }

    @GetMapping("/acknowledgements")
    public String acknowledge(){ return "acknowledgements"; }
}