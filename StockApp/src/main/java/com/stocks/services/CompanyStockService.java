package com.stocks.services;

import com.stocks.domain.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompanyStockService {
    String findName(String symbol);
    List<Stock> findPriceList(String symbol);
    ArrayList<Map<String, Integer>> getBoundaryDates(String symbol, List<Stock> stockList);
}
