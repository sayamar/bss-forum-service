package com.bss.service.mappers;


import com.bss.api.request_responses.CategoryRequest;
import com.bss.api.request_responses.CategoryResponse;
import com.bss.data.entities.Category;

public class CategoryMapper {

    /**
     * Converts a `CategoryRequest` to a `Category` entity for persistence.
     */
    public static Category toEntity(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            return null;
        }

        Category category = new Category();
        category.setName(categoryRequest.getName());
        return category;
    }

    /**
     * Converts a `Category` entity to a `CategoryResponse` DTO for API responses.
     */
    public static CategoryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }

        CategoryResponse response = new CategoryResponse();
        response.setCategoryId(category.getCategoryId());
        response.setName(category.getName());
        return response;
    }
}
