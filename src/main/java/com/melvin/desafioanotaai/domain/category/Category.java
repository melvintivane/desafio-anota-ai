package com.melvin.desafioanotaai.domain.category;

import com.melvin.desafioanotaai.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerID;
    private List<Product> productList;

    public Category(CategoryDTO categoryDTO) {
        this.title = categoryDTO.title();
        this.description = categoryDTO.description();
        this.ownerID = categoryDTO.ownerID();
    }
}
