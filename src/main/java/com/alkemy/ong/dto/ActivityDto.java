package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data @NoArgsConstructor @AllArgsConstructor
public class ActivityDto {

    private Long id;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String content;
    private String image;
}
