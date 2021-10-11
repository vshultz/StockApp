package com.stocks.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Attribute")
@Table(name = "attribute")
@Getter
@Setter
public class Attribute {
    @Id // companyName is primary key
    @GeneratedValue
    @Column(name = "name", updatable = false, nullable = false)
    private String companyName;

    @Column(name = "symbol", updatable = false, nullable = false, unique = true)
    private String symbol;

    @Column(name = "sector", updatable = false)
    private String sector;

    /** no-arg constructor **/
    public Attribute() {}
}
