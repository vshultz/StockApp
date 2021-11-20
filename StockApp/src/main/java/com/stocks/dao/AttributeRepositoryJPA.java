package com.stocks.dao;

import com.stocks.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepositoryJPA extends JpaRepository<Attribute, String> {
}
