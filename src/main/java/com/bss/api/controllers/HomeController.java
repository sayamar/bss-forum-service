package com.bss.api.controllers;


import com.bss.api.request_responses.BlogPost;
import com.bss.data.entities.Category;
import com.bss.data.repos.CategoryRepository;
import com.bss.data.repos.PostRepository;
import com.bss.service.PostService;
import com.bss.service.RSSFeed;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {


    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final PostService postService;
    private final RSSFeed rssFeed;

    @GetMapping("/board")
    public String getPosts(Model model){

        model.addAttribute("feed", rssFeed.fetchLatestPosts());
        return "board";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model,
                       @RequestParam(defaultValue = "0") int pageNumber,
                       @RequestParam(defaultValue = "10") int pageSize) {
        String username = (String) session.getAttribute("username");
        Long loginTime = (Long) session.getAttribute("loginTime");
        long now = System.currentTimeMillis();
        if (username == null || username.isEmpty() || loginTime == null || (now - loginTime) > 600000 ) {
            // No valid session, redirect to login page or error page
            return "redirect:/login";
        }
        model.addAttribute("username", username);

//        List<Post> posts = postRepository.findAll();
//        model.addAttribute("posts", posts);
        List<Category> categories = categoryRepository.findAllWithPostsAndUsers();
     //   List<Category> categories = postService.getPaginatedCategoriesWithPostsAndUsers(pageNumber, pageSize);

        // No need for extra initialization because of fetch join

        model.addAttribute("categories", categories);
        return "home";
    }

//    @GetMapping("/posts/{postId}")
//    public String viewPost(@PathVariable Long postId, HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        model.addAttribute("username", username);
//
//        Post post = postRepository.findById(postId).orElseThrow();
//        model.addAttribute("post", post);
//
//        return "post-detail";
//    }
}
