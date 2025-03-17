package com.Activepharmavskp.billing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String name;

    private String contains;

    private String pack;

    @Column(name = "bill_rate")
    private Double billRate;

    private Double mrp;

    @Column(name = "realized_rate_10")
    private Double realizedRate10;

    @Column(name = "realized_rate_15")
    private Double realizedRate15;
}
