package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialsCreationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TestimonialServiceImp implements TestimonialsService {
    @Override
    public TestimonialsService getTestimonialsById(Long id) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }

    @Override
    public TestimonialsResponseDto createTestimonials(TestimonialsCreationDto testimonialsCreationDto) {
        return null;
    }

    @Override
    public TestimonialsResponseDto updateTestimonials(Long id, TestimonialsCreationDto testimonialsCreationDto) {
        return null;
    }

    @Override
    public Page<TestimonialsService> showAllTestimonials(Pageable pageable) {
        return null;
    }
}
