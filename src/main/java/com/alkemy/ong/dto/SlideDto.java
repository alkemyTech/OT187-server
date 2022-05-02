package com.alkemy.ong.dto;

import com.alkemy.ong.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlideDto {

    private Long id;
    private String imageUrl;
    private String text;
    private Integer order;
    private Organization organization;
}