package com.stocks.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Price")
@Table(name = "price")
@Getter
@Setter
public class Price {
    @EmbeddedId // date, symbol is primary key
    @GeneratedValue
    private DateSymbol dateSymbol;

    @Column(name = "open", updatable = false, unique = true)
    private float open;

    @Column(name = "high", updatable = false, unique = true)
    private float high;

    @Column(name = "low", updatable = false, unique = true)
    private float low;

    @Column(name = "close", updatable = false, unique = true)
    private float close;

    @Column(name = "volume", updatable = false, unique = true)
    private int volume;
}