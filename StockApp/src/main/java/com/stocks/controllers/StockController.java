package com.stocks.controllers;

import com.stocks.dao.AttributeRepositoryJPA;
import com.stocks.domain.Attribute;
import com.stocks.domain.Stock;
import com.stocks.services.CompanyStockService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
@RestController
public class StockController {

    @Autowired
    private CompanyStockService companyStockService;

    @Autowired
    private AttributeRepositoryJPA attributeRepositoryJPA;

    // get company name by symbol rest api
    @GetMapping("/name/{symbol}")
    public ResponseEntity<Attribute> getCompanyName(@PathVariable String symbol) {
        Attribute attribute = attributeRepositoryJPA.findById(symbol)
                .orElseThrow(() -> new ResourceNotFoundException("Company does not exists with symbol: " + symbol));
        return ResponseEntity.ok(attribute);
    }

    // get list of stocks by symbol rest api
    @GetMapping("/stocks/{symbol}")
    public ResponseEntity<List> getStockList(@PathVariable String symbol) {
        List priceList = companyStockService.findPriceList(symbol);
        return ResponseEntity.ok(priceList);
    }

    // get all companies rest api
    @GetMapping("/allCompanies")
    public List<Attribute> getAllCompanyList() {
        return attributeRepositoryJPA.findAll();
    }

}
