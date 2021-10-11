package com.stocks.dao;

import com.stocks.domain.Stock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class StockRepository implements CompanyRepository {
    @PersistenceContext EntityManager entityManager;

    @Override
    public String findName(String symbol) {return null;}

    @Override
    public List<Stock> findPriceList(String symbol) {
        List<Stock> stockList = entityManager.createQuery("SELECT s FROM Stock s WHERE s.dateSymbol.symbol = :symbol order by s.dateSymbol.date")
                .setParameter("symbol", symbol)
                .getResultList();
        return stockList;
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
        System.out.println(dateList.get(0).get("year") + " " + dateList.get(1).get("year"));
        System.out.println(dateList.get(0).get("month") + " " + dateList.get(1).get("month"));
        System.out.println(dateList.get(0).get("day") + " " + dateList.get(1).get("day"));
        return dateList;
    }
}
