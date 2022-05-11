package com.alkemy.ong.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="members" )
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "facebookUrl")
    private String facebookUrl;
    @Column(name = "instagramUrl")
    private String instagramUrl;
    @Column(name = "linkedinUrl")
    private String linkedinUrl;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "timestamps", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Integer active = 1;

}
