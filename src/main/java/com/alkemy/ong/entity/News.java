package com.alkemy.ong.entity;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "The database generated news ID", example = "1", required = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @ApiModelProperty(notes = "News name", required = true)
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Content is required")
    @ApiModelProperty(notes = "News content", required = true)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The image URL of the news", example = "image.jps")
    private String image;

    @ApiModelProperty(notes = "Category assigned to the new", example = "Police")
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
    @ApiModelProperty(notes = "Timestamp of the news", example = "2022/05/16")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @ApiModelProperty(notes = "Determines if the new is active in the database or not", example = "1")
    private Integer active = 1;

}
