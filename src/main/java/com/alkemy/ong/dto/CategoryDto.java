package com.alkemy.ong.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryDto {

    private Long id;

    @Schema(description = "Name of the category", required = true, example = "Climate Change")
    private String name;

    @Schema(description = "Description of the category", example = "Climate change is a true problem", required = true)
    private String description;

    @Schema(description = "The image URL of the category", example = "climate_change.jpg", required = true)
    private String image;


    public CategoryDto(Long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}