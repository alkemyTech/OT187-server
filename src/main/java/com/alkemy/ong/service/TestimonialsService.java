
package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialsCreationDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestimonialsService {
    TestimonialsService getTestimonialsById(Long id);

    String deleteById(Long id);

    TestimonialsResponseDto createTestimonials(TestimonialsCreationDto testimonialsCreationDto);

    TestimonialsResponseDto updateTestimonials(Long id, TestimonialsCreationDto testimonialsCreationDto);

    Page<TestimonialsService> showAllTestimonials(Pageable pageable);
}


