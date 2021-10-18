package com.stocks.dao;

import com.stocks.domain.Attribute;
import com.stocks.domain.Stock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AttributeRepository implements CompanyRepository {
    @PersistenceContext EntityManager entityManager;

    @Override
    public List<Attribute> findAttribute(String symbol) {
        return entityManager.createQuery("SELECT a FROM Attribute a WHERE a.symbol = :symbol")
                .setParameter("symbol", symbol)
                .getResultList();
    }

    @Override
    public List<Stock> findPriceList(String symbol) { return null; }
}
