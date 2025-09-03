package com.bss.data.entities;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    // One category can have many posts
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

}
