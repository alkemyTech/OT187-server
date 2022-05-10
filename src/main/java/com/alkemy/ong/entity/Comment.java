package com.alkemy.ong.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;
    
    @NotBlank(message = "It is not possible to send an empty comment")
    private String body;
    
    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;
    
}
