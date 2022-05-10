package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.entity.Testimonial;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.repository.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.alkemy.ong.utility.Constantes.PAGE_SIZE;
import static com.alkemy.ong.utility.Constantes.PAGE_URL;

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
    @Transactional
    public TestimonialDto updateTestimonials(Long id, TestimonialDto testimonialDto) {

        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow((() -> new NotFoundException("Testimonial not found")));

        testimonial.setName(testimonialDto.getName());
        testimonial.setContent(testimonialDto.getContent());
        testimonial.setImageUrl(testimonialDto.getImageUrl());

        return testimonialMapper.testimonialToTestimonialDto(testimonialRepository.save(testimonial));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<TestimonialDto> getAll(Integer page) {

        if (page == null || page < 1) {
            throw new NotFoundException("Page must be greater than 0 and less than or equal to the total number of pages");
        }

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<Testimonial> testimonials = testimonialRepository.findAll(pageable);

        if (testimonials.isEmpty()) {
            throw new NotFoundException("The requested page does not exist");
        }

        Page<TestimonialDto> testimonialDto = testimonials.map(testimonialMapper::testimonialToTestimonialDto);

        return new PageResponseDto<>(testimonialDto, PAGE_URL);
    }

}
