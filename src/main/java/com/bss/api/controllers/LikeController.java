package com.bss.api.controllers;


import com.bss.api.request_responses.LikeRequest;
import com.bss.data.entities.Like;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import com.bss.data.repos.CommentRepository;
import com.bss.data.repos.LikeRepository;
import com.bss.data.repos.PostRepository;
import com.bss.data.repos.UserRepository;
import com.bss.service.mappers.LikeMapper;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/api/likes")
@AllArgsConstructor
public class LikeController {


    private final LikeRepository likeRepository;


    private final UserRepository userRepository;


    private final PostRepository postRepository;


    private final CommentRepository commentRepository;


    private final LikeMapper likeMapper;

    /**
     * Like a post
     */
    @PostMapping("/post")
    public Like likePost(@RequestParam Long userId, @RequestParam Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + postId));

        LikeRequest like = new LikeRequest();
//        like.setUser(user);
//        like.setPost(post);
//        like.setComment(null);
        return likeRepository.save(likeMapper.toEntity(like,user,post, null));
    }

    @PostMapping("/like")
    public String likePost(@RequestParam Long postId, HttpSession session) {
        String username = (String) session.getAttribute("username");  // Get logged in user id

        if (username == null) {
            // Redirect to login if no user in session
            return "redirect:/login";
        }

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        like.setCreatedAt(LocalDateTime.now());

        likeRepository.save(like);

        // Redirect back to post detail so user sees updated likes count
        return "redirect:/posts/" + postId;
    }
}
