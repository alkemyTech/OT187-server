package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SlideSlimDto {
    private Long id;
    private String imageUrl;
    private Integer order;

}
