package com.alkemy.ong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "slide")
@Data
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String imageUrl;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String text;

    @NotNull
    private Integer disposition;


    @ManyToOne (cascade=CascadeType.ALL)
    private Organization organization;


}
