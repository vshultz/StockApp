package com.stocks.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Stock")
@Table(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {
    @EmbeddedId // date, symbol is primary key
    @GeneratedValue
    private DateSymbol dateSymbol;

    @Column(name = "year", updatable = false)
    private int year;

    @Column(name = "month", updatable = false)
    private int month;

    @Column(name = "day", updatable = false)
    private int day;

    @Column(name = "open", updatable = false)
    private float open;

    @Column(name = "high", updatable = false)
    private float high;

    @Column(name = "low", updatable = false)
    private float low;

    @Column(name = "close", updatable = false)
    private float close;

    @Column(name = "volume", updatable = false)
    private int volume;
}