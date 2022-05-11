package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentDto {
    
    @JsonProperty("id")
    private Long id;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("newsId")
    private Long newsId;
    @JsonProperty("body")
    private String body;
    
}
