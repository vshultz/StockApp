package com.stocks.dao;

import com.stocks.domain.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompanyRepository {

    String findName(String symbol);
    List<Price> findPriceList(String symbol);
    ArrayList<Map<String, Integer>> getBoundaryDates(String symbol, List<Price> priceList);
}
