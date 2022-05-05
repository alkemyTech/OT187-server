
package com.alkemy.ong.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestimonialsCreationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "{testimonials.error.empty.name}")
    private String name;

    private MultipartFile image;

    private String content;

}


