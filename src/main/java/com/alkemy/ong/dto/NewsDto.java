package com.alkemy.ong.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class NewsDto {
    private Long id;
    @Schema(description = "News name", required = true)
    private String name;

    @Schema(description = "News content", required = true)
    private String content;

    @Schema(description = "The image URL of the news", example = "image.jpg")
    private String image;

    @Schema(description = "Type of news", example = "Climate")
    private String type;

    @Schema(description = "Category assigned to the new", example = "Climate Change")
    private CategoryDto category;

    @Schema(description = "Category id assigned to the new", example = "2")
    private Long categoryId;


}