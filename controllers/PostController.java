package com.bss.api.controllers;


import com.bss.api.request_responses.PostRequest;
import com.bss.data.entities.Category;
import com.bss.data.entities.Comment;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import com.bss.data.repos.CategoryRepository;
import com.bss.data.repos.CommentRepository;
import com.bss.data.repos.PostRepository;
import com.bss.data.repos.UserRepository;
import com.bss.service.mappers.PostMapper;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {


    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final PostMapper postMapper;



    @PostMapping
    public Post createPost(@RequestParam Long userId, @RequestBody PostRequest postRequest) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Category> categoryOptional = categoryRepository.findById(postRequest.getCategoryId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with id " + userId);
        }
        if(categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found with id " + postRequest.getCategoryId());
        }

        return postRepository.save(postMapper.toEntity(postRequest,userOpt.get(),categoryOptional.get()));
    }

    @GetMapping("/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model, HttpSession session) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        String username = (String) session.getAttribute("username");
        Long loginTime = (Long) session.getAttribute("loginTime");
        long now = System.currentTimeMillis();
        if (username == null || username.isEmpty() || loginTime == null || (now - loginTime) > 600000 ) {
            // No valid session, redirect to login page or error page
            return "redirect:/login";
        }

        List<Comment> comments = commentRepository.findByPost_PostId(postId);


        if (comments == null) {
            comments = new ArrayList<>();
        }

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());

        return "post-detail";  // Thymeleaf template
    }

    @PostMapping("/{postId}/comments")
    public String addComment(@PathVariable Long postId,
                             @ModelAttribute("newComment") Comment newComment,
                             HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = userRepository.findByUsername(username).
                orElseThrow(()-> new RuntimeException(username+ "NOT FOUND"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(postId+" Post not found"));

        newComment.setPost(post);
        newComment.setUser(user); // or fetch User
        newComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(newComment);

        return "redirect:/posts/" + postId;
    }

    @GetMapping("/category/{categoryId}")
    public String getPostsByCategory(@PathVariable Long categoryId, Model model, HttpSession session) {
        // Optional: check session here if needed

        String username = (String) session.getAttribute("username");
        Long loginTime = (Long) session.getAttribute("loginTime");
        long now = System.currentTimeMillis();
        if (username == null || username.isEmpty() || loginTime == null || (now - loginTime) > 600000 ) {
            // No valid session, redirect to login page or error page
            return "redirect:/login";
        }

        Category category1 = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Category> categories = categoryRepository.findAllWithPostsAndUsers();

        List<Category> filteredCategories = categories.stream()
                .map(category -> {
                    // Filter posts inside each category
                    List<Post> filteredPosts = category.getPosts().stream()
                            .filter(post -> post.getCategory().getCategoryId().equals(category1.getCategoryId()))
                            .collect(Collectors.toList());

                    // Update the category's posts to the filtered list
                    category.setPosts(filteredPosts);

                    return category;
                })
                .collect(Collectors.toUnmodifiableList());

        model.addAttribute("categories", categories);
        return "home";
    }

    @GetMapping("/show")
    public String showPostForm(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("postForm", new PostRequest());
        return "post-issue";
    }

    @PostMapping("/create")
    public String submitReport(@ModelAttribute("postForm") PostRequest postForm, HttpSession session) {
        // Retrieve user from session (assuming username saved in session)
        String username = (String) session.getAttribute("username");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(postForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setCategory(category);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        post.setStatus("Open");  // default status

        postRepository.save(post);

        return "redirect:/home";  // Redirect to homepage or post list page
    }


}
