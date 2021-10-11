package com.stocks.dao;

import com.stocks.domain.Price;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PriceRepository implements CompanyRepository {
    @PersistenceContext EntityManager entityManager;

    @Override
    public String findName(String symbol) {return null;}

    @Override
    public List<Price> findPriceList(String symbol) {
        List<Price> priceList = entityManager.createQuery("SELECT p FROM Price p WHERE p.dateSymbol.symbol = :symbol order by p.dateSymbol.date")
                .setParameter("symbol", symbol)
                .getResultList();
        return priceList;
    }
}
