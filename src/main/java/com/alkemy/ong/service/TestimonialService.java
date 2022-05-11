
package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.dto.TestimonialDto;

public interface TestimonialService {
    TestimonialDto getTestimonialsById(Long id);

    void deleteById(Long id);

    TestimonialDto createTestimonial(TestimonialDto testimonial);

    TestimonialDto updateTestimonials(Long id, TestimonialDto testimonial);

    PageResponseDto<TestimonialDto> getAll(Integer page);
}


