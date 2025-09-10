package com.bss.service;

import com.bss.api.request_responses.PostDTO;
import com.bss.data.entities.Category;
import com.bss.data.entities.Post;

import java.util.List;

public interface PostService {
     List<Category> getPaginatedCategoriesWithPostsAndUsers(int pageNumber,
                                                                  int pageSize);
     List<PostDTO> findAllWithUserAndCategory();

}
