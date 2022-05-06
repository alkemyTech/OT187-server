package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideSlimDto;
import com.alkemy.ong.entity.Slide;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SlideMapper {

    SlideDto SlideToSlideDto(Slide Slide);
    Slide SlideDtoToSlide(SlideDto SlideDto);
    List<SlideSlimDto> allSlidesToAllSlidesSlimDto(List<Slide> list);
    List<SlideDto> allSlidesToAllSlidesDto(List<Slide> list);
}
