package com.example.springrestapi.category;

import com.example.springrestapi.category.entity.Category;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::fromCategory)
                .toList();
    }

    public int addCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByNameAndSymbol(categoryDTO.getName(), categoryDTO.getSymbol())) {
            throw new IllegalArgumentException("Category with the same name and symbol already exists");
        }

        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setSymbol(categoryDTO.symbol());
        category.setDescription(categoryDTO.description());
        category = categoryRepository.save(category);

        return category.getId();
    }
}