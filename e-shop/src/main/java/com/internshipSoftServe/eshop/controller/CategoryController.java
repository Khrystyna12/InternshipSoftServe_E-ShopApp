package com.internshipSoftServe.eshop.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internshipSoftServe.eshop.model.Category;
import com.internshipSoftServe.eshop.repository.CategoryRepository;

@Controller
@RequestMapping(path="/shop")
public class CategoryController {

	private CategoryRepository categoryRepository;
	
	public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
	
	@GetMapping("/categories")
    public @ResponseBody Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @Valid @RequestBody Category category) {
    	Category categoryDB = update(categoryId, category);
        return categoryDB;
    }
    
    public Category update(Long id, Category category) {
        Category categoryFromDB = categoryRepository.findById(id);
        BeanUtils.copyProperties(category, categoryFromDB, "id", "products");
        return categoryRepository.save(categoryFromDB);
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryRepository.deleteById(categoryId);
    }
}
