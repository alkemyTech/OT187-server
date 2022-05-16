package com.alkemy.ong.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialDto {

    @Schema(description = "The id of the testimonial", example = "1")
    private Long id;

    @Schema(description = "The name of the testimonial", example = "John testimonial", required = true)
    private String name;

    @Schema(description = "Image that represents the testimonial", example = "com.image.jpg")
    private String imageUrl;

    @Schema(description = "Complete description of the testimonial", example = "This ong experience is amazing...")
    private String content;

}
