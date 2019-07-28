package com.internshipSoftServe.eshop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Category {
	public Category() {}

	public Category(@Size(max = 45) String name, @Size(max = 255) String description) {
		this.name = name;
		this.description = description;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@Column(unique = true, length = 45)
    @Size(max = 45)
	private String name;
	
	@Column(length = 255)
    @Size(max = 255)
	private String description;
	
	@ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
