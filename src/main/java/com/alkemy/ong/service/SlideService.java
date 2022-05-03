package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.exception.NotFoundException;

public interface SlideService {

    public SlideDto save(SlideDto slideDto);
    public SlideDto update(SlideDto SlideDto) throws NotFoundException;
    public void delete(Long id) throws NotFoundException;
}
