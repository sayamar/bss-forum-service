package com.bss.data.entities;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "post_id", "comment_id"}))
@Setter
@Getter
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Like either a post or a comment (exclusive)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

