package com.stocks.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class DateSymbol implements Serializable {

    @Column(name = "date", updatable = false, nullable = false)
    private Date date;

    @Column(name = "symbol", updatable = false, nullable = false)
    private String symbol;
}
