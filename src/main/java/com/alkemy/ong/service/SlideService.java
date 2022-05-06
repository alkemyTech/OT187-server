package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideSlimDto;
import com.alkemy.ong.exception.NotFoundException;

import java.util.List;

public interface SlideService {

    SlideDto save(SlideDto slideDto);
   SlideDto update(SlideDto SlideDto) throws NotFoundException;
   void delete(Long id) throws NotFoundException;
   List<SlideSlimDto> getAllSlides();
   SlideDto getSlideById(Long id);
   List<SlideDto> getSlidesByOrder(Long orgId);
}
