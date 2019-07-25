package com.internshipSoftServe.eshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.internshipSoftServe.eshop.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	Category findById(Long id);

	void deleteById(Long categoryId);
}
