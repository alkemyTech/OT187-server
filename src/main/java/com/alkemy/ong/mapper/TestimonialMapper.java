package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.entity.Testimonial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestimonialMapper {

    TestimonialDto testimonialToTestimonialDto(Testimonial testimonial);
    Testimonial testimonialDtoToTestimonial(TestimonialDto testimonialDto);
}
