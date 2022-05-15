package com.alkemy.ong.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Table(name = "news")
@Data
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Content is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    @JoinColumn(name = "category_id")
    private Category category;
    
    @Column(columnDefinition = "varchar(20) default 'News'")
    private String type;

    @Column(name= "creation_date" , nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    private Integer active = 1;

}
