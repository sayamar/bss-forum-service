package com.bss.service;


import com.bss.api.request_responses.CategoryRequest;
import com.bss.data.entities.Category;
import com.bss.data.repos.CategoryRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;



    public Category createCategory(CategoryRequest categoryRequest) {
        // Create a new category entity
        Category category = new Category();
        category.setName(categoryRequest.getName());

        // Save the category in the database
        return categoryRepository.save(category);
    }
}

