package com.stocks.dao;

import com.stocks.domain.Price;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class PriceRepository implements CompanyRepository {
    @PersistenceContext EntityManager entityManager;

    SimpleDateFormat year = new SimpleDateFormat("yyyy");
    SimpleDateFormat month = new SimpleDateFormat("mm");
    SimpleDateFormat day = new SimpleDateFormat("dd");

    @Override
    public String findName(String symbol) {return null;}

    @Override
    public List<Price> findPriceList(String symbol) {
        List<Price> priceList = entityManager.createQuery("SELECT p FROM Price p WHERE p.dateSymbol.symbol = :symbol order by p.dateSymbol.date")
                .setParameter("symbol", symbol)
                .getResultList();
        return priceList;
    }

    @Override
    public ArrayList<Map<String, Integer>> getBoundaryDates(String symbol, List<Price> priceList) {
        ArrayList<Map<String, Integer>> dateList = new ArrayList<Map<String, Integer>>();

        int idx = 0;
        while(idx < priceList.size()) {
            Map<String, Integer> dateMap = new HashMap<String, Integer>();
            Date date = priceList.get(idx).getDateSymbol().getDate();
            dateMap.put("year", Integer.parseInt(year.format(date)));
            dateMap.put("month", Integer.parseInt(month.format(date)));
            dateMap.put("day", Integer.parseInt(month.format(date)));
            dateList.add(dateMap);
            idx += priceList.size() - 1;
        }
        System.out.println(dateList.get(0).get("year") + " " + dateList.get(1).get("year"));
        System.out.println(dateList.get(0).get("month") + " " + dateList.get(1).get("month"));
        System.out.println(dateList.get(0).get("day") + " " + dateList.get(1).get("day"));
        return dateList;
    }
}
