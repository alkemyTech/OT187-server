package com.alkemy.ong.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Data
public class CategoryFullDto {

    private Long id;
    @NotBlank(message = "the name field cannot be empty")
    @NotNull(message = "field name cannot be null")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "The name cannot contain numbers or characters other than letters")
    private String name;
    private String description;
    private String image;
    private LocalDateTime timestamps;
    private boolean softDelete;
}
