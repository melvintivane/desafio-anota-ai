package com.melvin.desafioanotaai.domain.product;

import com.melvin.desafioanotaai.domain.category.Category;

public record ProductDTO(String title, String description, Integer price, String ownerID, String categoryID) {
}
