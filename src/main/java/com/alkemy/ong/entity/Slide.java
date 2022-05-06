package com.alkemy.ong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@AllArgsConstructor
@Table(name = "slides")
@NoArgsConstructor
public class Slide {

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
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


    public Slide(String imageUrl, String text, Integer order, Organization organization)
    {
        this.imageUrl = imageUrl;
        this.text = text;
        this.order = order;
        this.organization = organization;
    }
}
