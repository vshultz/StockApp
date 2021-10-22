package com.stocks.services;

import com.google.gson.Gson;
import com.stocks.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonExporterImpl implements JsonExporter {

    @Autowired
    CompanyStockService companyStockService;

    @Override
    public String getJsonStock(String symbol) {
        Gson gson = new Gson();
        List<Stock> stockList = companyStockService.findPriceList(symbol);
        return gson.toJson(stockList);
    }
//
//    @Override
//    public ResponseEntity<byte[]> createJson(String symbol) {
//        List<Stock> stockList = companyStockService.findPriceList(symbol);
//        String stockJsonString = getJsonStock(stockList);
//        byte[] stockJsonBytes = stockJsonString.getBytes();
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=stockData.json")
//                .contentType(MediaType.APPLICATION_JSON)
//                .contentLength(stockJsonBytes.length)
//                .body(stockJsonBytes);
//    }

}
