package com.alkemy.ong.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class CategoryFullDto {

    private Long id;
    private String name;
    private String description;
    private String image;
    private LocalDateTime timestamps;
    private boolean softDelete;
}
