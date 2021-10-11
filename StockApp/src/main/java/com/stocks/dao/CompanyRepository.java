package com.stocks.dao;

import com.stocks.domain.Price;

import java.util.List;

public interface CompanyRepository {

    String findName(String symbol);
    List<Price> findPriceList(String symbol);

}
