
package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialsCreationDto;
import com.alkemy.ong.service.TestimonialsResponseDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Configuration
public interface Testimonials {
    Testimonials getTestimonialsById(Long id);

    String deleteById(Long id);

    TestimonialsResponseDto createTestimonials(TestimonialsCreationDto testimonialsCreationDto);

    TestimonialsResponseDto updateTestimonials(Long id, TestimonialsCreationDto testimonialsCreationDto);

    Page<Testimonials> showAllTestimonials(Pageable pageable);
}


