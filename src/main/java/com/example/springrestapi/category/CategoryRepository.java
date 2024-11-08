package com.example.springrestapi.category;

import com.example.springrestapi.category.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
}
