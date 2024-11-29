package com.example.springrestapi.category;

import com.example.springrestapi.category.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;


public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    boolean existsByNameAndSymbol(String name, String symbol);
    List<Category> findByName(String name);
}
