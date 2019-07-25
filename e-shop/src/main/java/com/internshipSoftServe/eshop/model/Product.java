package com.internshipSoftServe.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    @Size(max = 45)
    private String name;

    private int price;

    private int quantity;

    @Column(length = 255)
    @Size(max = 255)
    private String description;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Category category;
}

