package com.melvin.desafioanotaai.controllers;

import com.melvin.desafioanotaai.domain.product.Product;
import com.melvin.desafioanotaai.domain.product.ProductDTO;
import com.melvin.desafioanotaai.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = productService.create(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.update(id, productDTO);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
