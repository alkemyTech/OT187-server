package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.List;

@Data
public class OrganizationDto {
    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("image")
    @NotNull
    private String image;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phone")
    private Integer phone;

    @JsonProperty("email")
    @Email
    @NotNull
    private String email;

    @JsonProperty("welcomeText")
    @NotNull
    private String welcomeText;

    @JsonProperty("aboutUsText")
    private String aboutUsText;

    @JsonProperty("facebookUrl")
    private String facebookUrl;
    
    @JsonProperty("linkedinUrl")
    private String linkedinUrl;
    
    @JsonProperty("instagramUrl")
    private String instagramUrl;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SlideDto> slides;

}
