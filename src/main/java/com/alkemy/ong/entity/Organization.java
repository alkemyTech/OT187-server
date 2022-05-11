package com.alkemy.ong.entity;

import lombok.Data;


import javax.persistence.*;

import java.time.LocalDateTime;

import java.util.List;

@Entity
@Table(name = "organization")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "welcomeText", nullable = false)
    private String welcomeText;

    @Column(name = "aboutUsText")
    private String aboutUsText;
    
    @Column(name = "facebook_url")
    private String facebookUrl;
    
    @Column(name = "linkedin_url")
    private String linkedinUrl;
    
    @Column(name = "instagram_url")
    private String instagramUrl;
    
    @Column(name = "timestamps", nullable = false)
    private LocalDateTime timestamps;

    @Column(name = "softDelete", nullable = false)
    private Integer softDelete;

    @OneToMany
    @JoinTable(name = "organization_slide", joinColumns = @JoinColumn(name = "organization_id"), inverseJoinColumns = @JoinColumn(name = "slide_id"))
    @OrderBy("disposition ASC")
    private List<Slide> slideList;

}
