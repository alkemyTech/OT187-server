package com.alkemy.ong.dto;

import lombok.Data;
@Data
public class NewsDto {
    private Long id;
    private String name;
    private String content;
    private String image;
    private String type;
    private CategoryDto category;
    private Long categoryId;


}