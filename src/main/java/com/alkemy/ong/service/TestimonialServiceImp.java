package com.alkemy.ong.service;

import com.alkemy.ong.dto.PagesDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.entity.Testimonial;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.repository.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public TestimonialDto getTestimonialById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow((() -> new NotFoundException("Testimonial not found")));
        return testimonialMapper.testimonialToTestimonialDto(testimonial);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow((() -> new NotFoundException("Testimonial not found")));
        testimonialRepository.deleteById(id);
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

    //Pagination of 10
    @Transactional
    public PagesDto<TestimonialDto> searchPaginatedTestimonial(int page) {
        if (page < 0) {
            throw new NotFoundException("The page number cannot be less than 0.");
        }
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Testimonial> testimonial = testimonialRepository.findAll(pageRequest);
        return responsePage(testimonial);
    }

    private PagesDto<TestimonialDto> responsePage(Page<Testimonial> page) {
        if (page.isEmpty()) {
            throw new NotFoundException("The page does not exist.");
        }
        Page<TestimonialDto> response = new PageImpl<>(
                testimonialMapper.listTestimonialToListTestimonialDto(page.getContent()),
                PageRequest.of(page.getNumber(), page.getSize()),
                page.getTotalElements());
        return new PagesDto<>(response, "localhost:8080/testimonials?page=");
    }

}
