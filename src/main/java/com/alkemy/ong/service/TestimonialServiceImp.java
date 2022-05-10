package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.entity.Testimonial;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.repository.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestimonialServiceImp implements TestimonialService {

    @Autowired
    private TestimonialsRepository testimonialRepository;
    @Autowired
    private TestimonialMapper testimonialMapper;
    @Override
    @Transactional(readOnly = true)
    public TestimonialDto getTestimonialsById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow((() -> new NotFoundException("Testimonial not found")));
        return testimonialMapper.testimonialToTestimonialDto(testimonial);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow((() -> new NotFoundException("Testimonial not found")));
        testimonialRepository.softDelete(id);
    }

    @Override
    @Transactional
    public TestimonialDto createTestimonial(TestimonialDto testimonialdto) {
        Testimonial testimonial = testimonialMapper.testimonialDtoToTestimonial(testimonialdto);
        return testimonialMapper.testimonialToTestimonialDto(testimonialRepository.save(testimonial));
    }

    @Override
    public TestimonialDto updateTestimonials(Long id, TestimonialDto testimonialDto) {

        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow((() -> new NotFoundException("Testimonial not found")));

        testimonial.setName(testimonialDto.getName());
        testimonial.setContent(testimonialDto.getContent());
        testimonial.setImageUrl(testimonialDto.getImageUrl());

        return testimonialMapper.testimonialToTestimonialDto(testimonialRepository.save(testimonial));
    }

}
