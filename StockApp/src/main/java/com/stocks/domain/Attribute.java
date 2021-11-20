package com.stocks.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Attribute")
@Table(name = "attribute")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attribute {
    @Id // companyName is primary key
    @GeneratedValue
    @Column(name = "symbol", updatable = false, nullable = false, unique = true)
    private String symbol;

    @Column(name = "name", updatable = false, nullable = false)
    private String companyName;

    @Column(name = "sector", updatable = false)
    private String sector;
}
