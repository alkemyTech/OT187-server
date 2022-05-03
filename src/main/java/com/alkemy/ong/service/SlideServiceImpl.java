package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.repository.SlideRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;

    public SlideServiceImpl(SlideRepository slideRepository)
    {
        this.slideRepository = slideRepository;
    }


    @Override
    public SlideDto save(SlideDto slideDto)
    {
        return null;
    }

    @Override
    public SlideDto update(SlideDto slideDto) throws NotFoundException
    {
        return null;
    }

    @Override
    public void delete(Long id) throws NotFoundException
    {

    }
}
