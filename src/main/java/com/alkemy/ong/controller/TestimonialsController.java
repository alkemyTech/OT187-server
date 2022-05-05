
package com.alkemy.ong.controller;



import com.alkemy.ong.dto.TestimonialsCreationDto;
import com.alkemy.ong.service.Testimonials;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Testimonial controller")
@RequestMapping("/testimonials")
public class TestimonialsController {

    private final Testimonials iTestimonials;
    private final MessageSource messageSource;

    @Autowired
    public TestimonialsController(Testimonials iTestimonials, MessageSource messageSource) {
        this.iTestimonials = iTestimonials;
        this.messageSource = messageSource;
    }

    @PostMapping
    @ApiOperation("Create a new testimonial")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<?> createTestimonials(@ApiParam(value = "JSON con Testimonial para crear", required = true) @ModelAttribute(name = "testimonialsCreationDto") @Valid TestimonialsCreationDto testimonialsCreationDto) {
        try {
             return ResponseEntity.status(HttpStatus.CREATED).body(iTestimonials.createTestimonials(testimonialsCreationDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation("Update a testimonial")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 404, message = "Bad Request")
    })
    public ResponseEntity<?> Update(@ApiParam(value = "El id del testimonio", required = true, example = "1") @ModelAttribute(name = "testimonialsCreationDto") @Valid TestimonialsCreationDto testimonialsCreationDto, @PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iTestimonials.updateTestimonials(id, testimonialsCreationDto));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}