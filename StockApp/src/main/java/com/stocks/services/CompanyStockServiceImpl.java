package com.stocks.services;

import com.stocks.dao.CompanyRepository;
import com.stocks.domain.Attribute;
import com.stocks.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyStockServiceImpl implements CompanyStockService {
    @Qualifier("attributeRepository")
    @Autowired
    CompanyRepository attributeRepository;

    @Qualifier("stockRepository")
    @Autowired
    CompanyRepository stockRepository;

    @Override
    public String findName(String symbol) {
        List<Attribute> attributeList = attributeRepository.findAttribute(symbol);
        return attributeList.get(0).getCompanyName();
    }

    @Override
    public List<Stock> findPriceList(String symbol) {

        return stockRepository.findPriceList(symbol);
    }

    @Override
    public ArrayList<Map<String, Integer>> getBoundaryDates(String symbol, List<Stock> stockList) {
        ArrayList<Map<String, Integer>> dateList = new ArrayList<Map<String, Integer>>();
        int idx = 0;
        while(idx < stockList.size()) {
            Map<String, Integer> dateMap = new HashMap<String, Integer>();
            dateMap.put("year", stockList.get(idx).getYear());
            dateMap.put("month", stockList.get(idx).getMonth());
            dateMap.put("day", stockList.get(idx).getDay());
            dateList.add(dateMap);
            idx += stockList.size() - 1;
        }
        return dateList;
    }
}
