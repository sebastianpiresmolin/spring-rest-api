package com.example.springrestapi.category;

import com.example.springrestapi.category.entity.Category;

import org.springframework.stereotype.Service;
import java.util.List;


public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::fromCategory)
                .toList();
    }

    public int addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setSymbol(categoryDTO.symbol());
        category.setDescription(categoryDTO.description());
        category = categoryRepository.save(category);
        return category.getId();
    }
}
