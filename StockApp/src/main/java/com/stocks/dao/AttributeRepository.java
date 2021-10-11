package com.stocks.dao;

import com.stocks.domain.Attribute;
import com.stocks.domain.Stock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AttributeRepository implements CompanyRepository {
    @PersistenceContext EntityManager entityManager;

    @Override
    public String findName(String symbol) {
        List<Attribute> attributeList = entityManager.createQuery("SELECT a FROM Attribute a WHERE a.symbol = :symbol")
                .setParameter("symbol", symbol)
                .getResultList();
        return attributeList.get(0).getCompanyName();
    }

    @Override
    public List<Stock> findPriceList(String symbol) { return null; }

    @Override
    public ArrayList<Map<String, Integer>> getBoundaryDates(String symbol, List<Stock> stockList) { return null; }

}
