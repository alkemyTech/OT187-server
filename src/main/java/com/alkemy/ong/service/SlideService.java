package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.entity.Slide;

public interface SlideService {

    static SlideDto editById(Long id, SlideDto slideDto){

        return slideDto;
    }

    SlideDto save(SlideDto slideDto);
    void delete(Long id);
    SlideDto findById(Long id);
    SlideDto update(Long id, SlideDto slideDto);
    Slide getAll();
}
