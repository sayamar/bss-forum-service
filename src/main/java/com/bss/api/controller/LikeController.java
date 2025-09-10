package com.bss.api.controller;



import com.bss.api.request_responses.LikeDTO;
import com.bss.data.entities.Like;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import com.bss.data.repos.LikeRepository;
import com.bss.data.repos.PostRepository;
import com.bss.data.repos.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/likes")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class LikeController {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> addLike(@RequestParam Long postId, @RequestParam Long userId) {

        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Post not found with id: " + postId);
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found with id: " + userId);
        }

        // Optional: Check if user already liked this post
        boolean alreadyLiked = likeRepository.existsById(userId); // Or implement custom query
        if (alreadyLiked) {
            return ResponseEntity.badRequest().body("User has already liked this post.");
        }

        Like like = new Like();
        like.setPost(postOpt.get());
        like.setUser(userOpt.get());

        Like savedLike = likeRepository.save(like);

        LikeDTO dto = new LikeDTO(savedLike.getLikeId(), postId, userId);

        return ResponseEntity.ok(dto);
    }
}
