package com.alkemy.ong.dto;

import com.alkemy.ong.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlideFullDto {

    private Long id;
    @NotBlank(message = "An image encoded in base64 must be specified.")
    private String imageUrl;
    @NotBlank(message = "A text for the slide must be provided.")
    private String text;
    private Integer order;
    private Organization organizationId;

}
