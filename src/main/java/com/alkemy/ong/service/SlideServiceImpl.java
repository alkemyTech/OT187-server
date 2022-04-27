package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.entity.Slide;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SlideServiceImpl implements SlideService{

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private SlideMapper slideMapper;


    @Transactional
    @Override
    public SlideDto save(SlideDto slideDto) {
        Slide slide = slideMapper.slideDtoToSlide(slideDto);
        return slideMapper.slideToSlideDto(slideRepository.save(slide));
    }


    @Transactional(readOnly = true)
    @Override
    public SlideDto findById(Long id) {
        Slide slide = slideRepository.findById(id).orElseThrow(RuntimeException::new);
        return slideMapper.slideToSlideDto(slide);
    }



    public SlideDto update(Long id, SlideDto slideDto) {
        Slide slide = slideMapper.slideDtoToSlide(slideDto);
        slide.setImageUrl(slideDto.getImageUrl());
        slide.setOrder(slideDto.getOrder());
        slide.setId(slide.getId());
        slideRepository.save(slide);
        return slideMapper.slideToSlideDto(slide);

    }

    @Override
    public Slide getAll(){
        List<Slide> slideList = slideRepository.findAll();
        List<SlideDto> slideDtoList = slideMapper.slideListToDtoList(slideList,false);
        return (Slide) slideDtoList;
    }

    @Override
    public void delete(Long id) {
        slideRepository.findById(id).orElseThrow(RuntimeException::new);
        slideRepository.softDelete(id);
    }


}
