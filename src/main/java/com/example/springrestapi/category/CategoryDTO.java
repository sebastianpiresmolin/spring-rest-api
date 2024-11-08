package com.example.springrestapi.category;

import com.example.springrestapi.category.entity.Category;

public record CategoryDTO(String name, String symbol, String description) {

    public static CategoryDTO fromCategory(Category category) {
        return new CategoryDTO(category.getName(), category.getSymbol(), category.getDescription());
    }
}
