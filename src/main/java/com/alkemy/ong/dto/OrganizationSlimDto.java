package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.NotNull;

import java.util.List;


@Data
public class OrganizationSlimDto {
    @JsonProperty("name")
    @NotNull
    private String name;
    @JsonProperty("image")
    @NotNull
    private String image;
    @JsonProperty("phone")
    private Integer phone;
    @JsonProperty("address")
    private String adress;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SlideSlimDto> slides;
}
