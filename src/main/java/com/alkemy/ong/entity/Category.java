package com.alkemy.ong.entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@SQLDelete(sql="UPDATE categories SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete=false")
@Getter
@Setter

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( nullable = false, length = 35)
    private String name;

    @Column(name = "description" , nullable = false)
    private String description;

    @Column(name = "image" , nullable = false)
    private String image;

    @Column(name= "timestamps" ,nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamps;

    @Column(name = "active")
    private Integer active;

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
