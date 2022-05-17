package com.alkemy.ong.entity;


import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "The unique id of the Member")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @ApiModelProperty(notes = "The Member's name",required = true,position = 1)
    private String name;
    
    @Column(name = "facebookUrl")
    @ApiModelProperty(notes = "The Member's facebook url",position = 2)
    private String facebookUrl;
    
    @Column(name = "instagramUrl")
    @ApiModelProperty(notes = "The Member's instagram url",position = 3)
    private String instagramUrl;
    
    @Column(name = "linkedinUrl")
    @ApiModelProperty(notes = "The Member's instagram url",position = 4)
    private String linkedinUrl;
    
    @Column(name = "image", nullable = false)
    @ApiModelProperty(notes = "The Member's image",required = true,position = 5)
    private String image;
    
    @Column(name = "description")
    @ApiModelProperty(notes = "The Member's description",position = 6)
    private String description;
    
    @Column(name = "timestamps", nullable = false)
    @ApiModelProperty(notes = "The Member's timestamp creation",position = 7)
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Integer active = 1;

}
