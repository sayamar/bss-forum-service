package com.bss.api.controllers;


import com.bss.api.request_responses.CommentRequest;
import com.bss.data.entities.Comment;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import com.bss.data.repos.CommentRepository;
import com.bss.data.repos.PostRepository;
import com.bss.data.repos.UserRepository;
import com.bss.service.mappers.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {


    private CommentRepository commentRepository;


    private PostRepository postRepository;

    private final CommentMapper commentMapper;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Comment createComment(@RequestParam Long userId, @RequestParam Long postId, @RequestBody CommentRequest commentRequest) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Post> postOpt = postRepository.findById(postId);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with id " + userId);
        }
        if (postOpt.isEmpty()) {
            throw new RuntimeException("Post not found with id " + postId);
        }

        return commentRepository.save(commentMapper.toEntity(commentRequest,postOpt.get(),userOpt.get()));
    }
}
