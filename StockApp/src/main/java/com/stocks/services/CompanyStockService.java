package com.stocks.services;

import com.stocks.domain.Stock;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompanyStockService {
    String findName(String symbol);
    String findSymbol(String name);
    List<Stock> findPriceList(String symbol);
    String writeJSONData(String title, String symbol) throws FileNotFoundException;
}
