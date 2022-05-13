
package com.alkemy.ong.response.dto;
import java.util.Date;

public interface CategoryResponseDto {

    Long getId();
    String getName();
    String getDescription();
    String getImage();
    Date getCreated();
    Date getEdited();
    Boolean getDeleted();


}


