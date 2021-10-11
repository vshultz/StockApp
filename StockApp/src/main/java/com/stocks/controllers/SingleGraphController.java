package com.stocks.controllers;

import com.stocks.dao.CompanyRepository;
import com.stocks.domain.Price;
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

    @Qualifier("priceRepository")
    @Autowired
    CompanyRepository priceRepository;

    @PostMapping("/home")
    public String getCompanyInfo(@RequestParam String symbol, Model model) {
        try {
            model.addAttribute("companyName", attributeRepository.findName(symbol));
            model.addAttribute("priceList", priceRepository.findPriceList(symbol));
            return "responses/singleGraph";
        } catch (IndexOutOfBoundsException i) {
            return "responses/error";
        }
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

}