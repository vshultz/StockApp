package com.stocks.dao;

import com.stocks.domain.Attribute;
import com.stocks.domain.Stock;

import java.util.List;

public interface CompanyRepository {
    List<Attribute> findName(String symbol);
    List<Attribute> findSymbol(String name);
    List<Stock> findPriceList(String symbol);
}
