package com.alkemy.ong.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberDto {
    private Long id;

    @Schema(description = "The Member's name",required = true, example = "John Doe")
    private String name;

    @Schema(description = "The Member's facebook url", example = "https://www.google.com/john.doe")
    private String facebookUrl;

    @Schema(description = "The Member's instagram url", example = "https://www.instagram.com/john.doe")
    private String instagramUrl;

    @Schema(description = "The Member's LinkedIn url", example = "https://www.linkedin.com/john.doe")
    private String linkedinUrl;

    @Schema(description = "The Member's image",required = true, example = "johndoe.jpg")
    private String image;

    @Schema(description = "The Member's description", example = "John Doe is a good guy")
    private String description;
    

}