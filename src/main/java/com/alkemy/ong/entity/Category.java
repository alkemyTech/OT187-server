package com.alkemy.ong.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@Getter
@Setter

public class Category {

    @Id
    @Schema(description = "The database generated category ID", example = "1", required = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Schema(description = "Name of the category", required = true)
    @Column( nullable = false, length = 35)
    private String name;

    @Schema(description = "Description of the category", example = "Police", required = true)
    @Column(name = "description" , nullable = false)
    private String description;

    @Schema(description = "The image URL of the category", example = "imagen.jpg", required = true)
    @Column(name = "image" , nullable = false)
    private String image;

    @Schema(description = "Timestamp of the category", example = "2022/05/16", required = true)
    @Column(name= "timestamps" ,nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamps;

    @Schema(description = "Determines if the category is active in the database or not", example = "1")
    @Column(name = "active")
    private Integer active = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category(Long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
