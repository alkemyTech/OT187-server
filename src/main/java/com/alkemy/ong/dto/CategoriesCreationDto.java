package com.alkemy.ong.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoriesCreationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{categories.error.empty.name}")
	private String name;

	private String description;

	private MultipartFile image;

   

}

