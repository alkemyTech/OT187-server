package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideFullDto;
import com.alkemy.ong.dto.SlidePublicOrganizationDto;
import com.alkemy.ong.entity.Slide;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SlideMapper {

    Slide slideDtoToSlide(SlideDto slideDto);

    SlideDto slideToSlideDto(Slide slide);

    SlideFullDto slideToSlideFullDto(Slide slide);

    Slide slideFullDtoToSlide(SlideFullDto dto);

    List<SlideDto> slideListToDtoList(List<Slide> slideList, boolean b);

    List<SlidePublicOrganizationDto> listSlideToListSlidePublicOrganizationDto(List<Slide> slideList);
}
