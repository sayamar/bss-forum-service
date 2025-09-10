package com.bss.api.controller;



import com.bss.api.request_responses.CommentDTO;
import com.bss.data.entities.Comment;
import com.bss.data.entities.Post;
import com.bss.data.entities.User;
import com.bss.data.repos.CommentRepository;
import com.bss.data.repos.PostRepository;
import com.bss.data.repos.UserRepository;
import com.bss.service.mappers.CommentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://bss-tech.ap01.fujifilm-intra.com")
@Slf4j
public class CommentController {


    private final CommentRepository commentRepository;


    private final PostRepository postRepository;


    private final UserRepository userRepository;

    private final CommentMapper commentMapper;




    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO) {
        log.info("Adding the comment");
        // Validate Post
        Optional<Post> postOpt = postRepository.findById(commentDTO.getPostId());
        if (postOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Post not found with id: " + commentDTO.getPostId());
        }

        // Validate User
        Optional<User> userOpt = userRepository.findById(commentDTO.getUserId());
        if (userOpt.isEmpty()) {
            if (log.isErrorEnabled()) {
                log.error("User not found with id: " + commentDTO.getUserId());
            }
            return ResponseEntity.badRequest().body("User not found with id: " + commentDTO.getUserId());
        }

        Comment comment = new Comment();
        comment.setPost(postOpt.get());
        comment.setUser(userOpt.get());
        comment.setContent(commentDTO.getComment());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        log.info("Comment posted successfully...");
        List<Comment> comments = commentRepository.findByPost_PostIdOrderByCreatedAtDesc(commentDTO.getPostId());
        List<CommentDTO> commentDTOs = comments.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentDTOs);
    }
}
