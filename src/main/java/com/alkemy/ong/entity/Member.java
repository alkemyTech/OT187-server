package com.alkemy.ong.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="members" )
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique id of the Member")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @Schema(description = "The Member's name",required = true)
    private String name;

    @Column(name = "facebookUrl")
    @Schema(description = "The Member's facebook url")
    private String facebookUrl;

    @Column(name = "instagramUrl")
    @Schema(description = "The Member's instagram url")
    private String instagramUrl;

    @Column(name = "linkedinUrl")
    @Schema(description = "The Member's instagram url")
    private String linkedinUrl;

    @Column(name = "image", nullable = false)
    @Schema(description = "The Member's image",required = true)
    private String image;

    @Column(name = "description")
    @Schema(description = "The Member's description")
    private String description;

    @Column(name = "timestamps", nullable = false)
    @Schema(description = "The Member's timestamp creation")
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Integer active = 1;

}
