package com.stocks.dao;

import com.stocks.domain.Attribute;
import com.stocks.domain.Price;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public List<Price> findPriceList(String symbol) {return null;}
}
