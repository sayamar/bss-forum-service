package com.bss.api.controller;

import com.bss.api.request_responses.BlogPost;
import com.bss.service.RSSFeed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    private final RSSFeed rssFeed;

    @GetMapping("/feed")
    public ResponseEntity<List<BlogPost>> getAllBlogs() {
        List<BlogPost> blogs = rssFeed.fetchLatestPosts();
        if ( blogs != null && !blogs.isEmpty()) {
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
