package com.internshipSoftServe.eshop.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class OrderItem {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
	private int quantity;
	
	@ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Order order;
	
	@ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Product product;
}
