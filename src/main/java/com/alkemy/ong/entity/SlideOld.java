package com.alkemy.ong.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;



/*
@Entity
@Table(name = "slide")
@Data
public class SlideOld {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String imageUrl;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String text;

    @NotNull
    private Integer order;

    @NotNull
    //@ManyToOne
    //@JoinColumn(name = "organization_id")
    @Column(name = "organization_id")
    private Integer organization;



}

 */
