package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;
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
}
