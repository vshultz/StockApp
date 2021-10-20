package com.stocks.stockapp;

import com.stocks.dao.AttributeRepository;
import com.stocks.domain.Attribute;
import com.stocks.services.CompanyStockService;
import com.stocks.services.CompanyStockServiceImpl;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;


public class AttributeTest {
    private AttributeRepository attributeRepository;
    private CompanyStockServiceImpl service;

    @Mock
    private Attribute attribute;
    private NullPointerException exception;
    private void assertThrows(NullPointerException exception, int numZero) {}
    @BeforeEach
    public void init() {
        attribute = new Attribute();
    }

    @Test
    public void testComeGetSome() {

        EntityManager em = Mockito.mock(EntityManager.class);
        String inputName = "garmin";
        Mockito.when(em.createQuery("SELECT a FROM Attribute a WHERE LOWER( a.companyName) LIKE :name")
                .setParameter("name", inputName)
                .getResultList()).thenReturn((List) attribute);

        attributeRepository = Mockito.mock(AttributeRepository.class);
        Mockito.when(attributeRepository.findSymbol(inputName)).thenCallRealMethod();
        Mockito.when(attributeRepository.getEntityManager()).thenReturn(em);

        assertEquals(attribute, attributeRepository.findSymbol(inputName));
    }
}
