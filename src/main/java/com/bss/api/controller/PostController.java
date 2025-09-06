package com.bss.api.controller;

import com.bss.api.request_responses.CategoryRequest;
import com.bss.api.request_responses.PostRequest;
import com.bss.data.entities.Category;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import com.bss.data.repos.CategoryRepository;
import com.bss.data.repos.PostRepository;
import com.bss.data.repos.UserRepository;
import com.bss.util.Constants;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    @PostMapping("/posts")
    public ResponseEntity<List<Post>> create(@RequestBody PostRequest postRequest) {
        // Retrieve user from session (assuming username saved in session)
        User user = userRepository.findById(postRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(postRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCategory(category);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        post.setStatus(Constants.Open);  // default status

        postRepository.save(post);

        return new ResponseEntity<>(postRepository.findAll().stream().toList(), HttpStatus.OK);
    }
    @GetMapping("listCategories")
    public ResponseEntity<List<CategoryRequest>> getCategories() {
        List<CategoryRequest> categoryRequestList = null;
        List<Category> categorList = categoryRepository.findAll();
        if(categorList != null && !categorList.isEmpty()) {
             categoryRequestList = categorList.stream()
                    .map(cat -> new CategoryRequest(String.valueOf(cat.getCategoryId()), cat.getName()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(categoryRequestList, HttpStatus.OK);
        }
        return new ResponseEntity<>(categoryRequestList, HttpStatus.OK);
    }
}
