package com.bss.data.entities;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Comments")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    // getters and setters
    // constructor(s)
}
