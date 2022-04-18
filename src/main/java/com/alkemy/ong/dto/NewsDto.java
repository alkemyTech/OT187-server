package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDto {
    private Long id;
    private String name;
    private String content;
    private String image;
    private CategoryDto categoryId;

}