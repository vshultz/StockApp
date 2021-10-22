package com.stocks.services;

import com.stocks.domain.Stock;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JsonExporter {

    String getJsonStock(String symbol);
//    ResponseEntity<byte[]> createJson(String symbol);
}
