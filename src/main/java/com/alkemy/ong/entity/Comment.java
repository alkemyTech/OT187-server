package com.alkemy.ong.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "user_id")
    private Long userId;
    
    @NotNull
    @Column(name = "news_id")
    private Long newsId;
    
    @NotBlank(message = "It is not possible to send an empty comment")
    private String body;
    
}
