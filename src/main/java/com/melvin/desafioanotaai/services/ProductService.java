package com.melvin.desafioanotaai.services;

import com.melvin.desafioanotaai.domain.category.Category;
import com.melvin.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.melvin.desafioanotaai.domain.product.Product;
import com.melvin.desafioanotaai.domain.product.ProductDTO;
import com.melvin.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.melvin.desafioanotaai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(CategoryService categoryService, ProductRepository repository) {
        this.categoryService = categoryService;
        this.repository = repository;
    }

    public Product create(ProductDTO productDTO) {
        Product newProduct = new Product(productDTO);
        Category category = this.categoryService.getById(productDTO.categoryID())
                .orElseThrow(CategoryNotFoundException::new);
        newProduct.setCategory(category);
        this.repository.save(newProduct);

        return newProduct;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public Optional<Product> getById(String id) {
        return this.repository.findById(id);
    }

    public Product update(String id, ProductDTO productDTO) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(productDTO.categoryID() != null) {
            this.categoryService.getById(productDTO.categoryID()).ifPresent(product::setCategory);
        }

        if(!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
        if(!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
        if(!(product.getPrice() == null)) product.setPrice(productDTO.price());

        this.repository.save(product);

        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }

}
