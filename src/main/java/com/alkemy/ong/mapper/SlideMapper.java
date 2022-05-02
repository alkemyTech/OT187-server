package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.entity.Slide;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SlideMapper {

    SlideDto SlideToSlideDto(Slide Slide);

    Slide SlideDtoToSlide(SlideDto SlideDto);
}