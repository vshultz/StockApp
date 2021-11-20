package com.stocks.services;

import java.io.FileNotFoundException;
import java.util.List;

public interface CompanyStockService {
    String findSymbol(String name);
    List findPriceList(String symbol);
    String writeJSONData(String title, String symbol) throws FileNotFoundException;
}
