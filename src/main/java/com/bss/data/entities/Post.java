package com.bss.data.entities;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "Posts")
@Setter
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false) // link to category table
    private Category category;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @Transient
    private int likesCount = 5;

    public int getLikesCount() {
        return likes.size();
    }

    public String getFormattedCreatedAt() {
        if (createdAt == null) return "Date not available";
        return createdAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    // getters and setters
    // constructor(s)
}

