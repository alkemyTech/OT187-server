package com.alkemy.ong.entity;

import lombok.Data;


import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
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

    @Column(name = "timestamps", nullable = false)
    private LocalDateTime timestamps;

    @Column(name = "softDelete", nullable = false)
    private Integer softDelete;




}
