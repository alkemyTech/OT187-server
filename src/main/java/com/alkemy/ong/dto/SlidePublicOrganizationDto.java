package com.alkemy.ong.dto;

import lombok.Data;

@Data
public class SlidePublicOrganizationDto {

    private Long id;
    private String imageUrl;
    private String text;
    private Integer order;
    private OrganizationPublicDto organizationId;
}
