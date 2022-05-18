
package com.alkemy.ong.service;

import com.alkemy.ong.dto.PagesDto;
import com.alkemy.ong.dto.TestimonialDto;

public interface TestimonialService {
    TestimonialDto getTestimonialById(Long id);

    void deleteById(Long id);

    TestimonialDto createTestimonial(TestimonialDto testimonial);

    TestimonialDto updateTestimonials(Long id, TestimonialDto testimonial);

    PagesDto<TestimonialDto> searchPaginatedTestimonial(int page);
}


