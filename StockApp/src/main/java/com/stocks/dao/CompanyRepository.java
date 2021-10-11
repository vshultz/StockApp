package com.stocks.dao;

import com.stocks.domain.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompanyRepository {

    String findName(String symbol);
    public List<Stock> findPriceList(String symbol);
    ArrayList<Map<String, Integer>> getBoundaryDates(String symbol, List<Stock> stockList);
}
