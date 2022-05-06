package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.entity.Slide;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SlideServiceImpl implements SlideService{

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private SlideMapper slideMapper;

    @Autowired
    private OrganizationRepository organizationRepository;

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

    public Slide getSlidesForOrganizationByOrder(Long organizationId)  {
        if (!organizationRepository.findById(organizationId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There is no Organization with the entered Id");
        } else {
            List<Slide> slideList;
            slideList = slideRepository.findSlideByOrganizationId(organizationId);
            return (Slide) slideMapper.listSlideToListSlidePublicOrganizationDto(slideList);
        }
    }

    @Override
    public void delete(Long id) {
        slideRepository.findById(id).orElseThrow(RuntimeException::new);
        slideRepository.softDelete(id);
    }


}
