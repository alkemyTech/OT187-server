package com.alkemy.ong.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private String image;
    private CategoryDto categoryDto;

}