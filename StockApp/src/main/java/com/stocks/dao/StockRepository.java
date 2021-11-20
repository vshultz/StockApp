package com.stocks.dao;

import com.stocks.domain.Attribute;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class StockRepository implements CompanyRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Attribute> findSymbol(String name) { return null; }

    @Override
    public List findPriceList(String symbol) {
        return entityManager.createQuery("SELECT s FROM Stock s WHERE s.dateSymbol.symbol = :symbol order by s.dateSymbol.date")
                .setParameter("symbol", symbol)
                .getResultList();
    }
}
