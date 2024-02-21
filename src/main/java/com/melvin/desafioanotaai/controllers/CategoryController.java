package com.melvin.desafioanotaai.controllers;

import com.melvin.desafioanotaai.domain.category.Category;
import com.melvin.desafioanotaai.domain.category.CategoryDTO;
import com.melvin.desafioanotaai.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category newCategory = categoryService.create(categoryDTO);
        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> allCategories = categoryService.getAll();
        return ResponseEntity.ok().body(allCategories);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") String id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
