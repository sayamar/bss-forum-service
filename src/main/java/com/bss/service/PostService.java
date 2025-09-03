package com.bss.service;

import com.bss.data.entities.Category;

import java.util.List;

public interface PostService {
     List<Category> getPaginatedCategoriesWithPostsAndUsers(int pageNumber,
                                                                  int pageSize);

}
