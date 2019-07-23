package com.internshipSoftServe.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internshipSoftServe.eshop.model.Category;
import com.internshipSoftServe.eshop.repository.CategoryRepository;

@Controller
@RequestMapping(path="/category")
public class CategoryController {

	private CategoryRepository categoryRepository;
	
	public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        Category n = new Category();
        n.setName("Category1");
        n.setDescription("Description of category1");
        categoryRepository.save(n);
    }
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewCategory (@RequestParam String name, @RequestParam String description) {
		Category n = new Category();
		n.setName(name);
		n.setDescription(description);
		categoryRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
