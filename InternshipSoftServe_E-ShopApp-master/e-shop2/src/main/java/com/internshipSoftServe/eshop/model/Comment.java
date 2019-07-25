package com.internshipSoftServe.eshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Comment {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
}
