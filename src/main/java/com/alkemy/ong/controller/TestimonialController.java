
package com.alkemy.ong.controller;


import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.TestimonialService;
//import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alkemy.ong.utility.Constantes.*;

import javax.validation.Valid;

//@Api(value = "Testimonial controller")
@Tag(name = "Testimonial")
@RestController
@RequestMapping(TESTIMONIAL_URL)
public class TestimonialController {

    private final TestimonialService iTestimonialService;
    private final MessageSource messageSource;

    @Autowired
    public TestimonialController(TestimonialService iTestimonialService, MessageSource messageSource) {
        this.iTestimonialService = iTestimonialService;
        this.messageSource = messageSource;
    }

    @Operation(summary = "Save a new testimonial")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Testimonial saved succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestimonialDto.class)) }),
            @ApiResponse(
                    responseCode = "409",
                    description = "Error while saving testimonial"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<?> createTestimonials(/*@ApiParam(value = "JSON con Testimonial para crear", required = true)*/ @Valid @RequestBody TestimonialDto testimonialDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iTestimonialService.createTestimonial(testimonialDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Update a testimonial")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Testimonial updated succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestimonialDto.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Testimonial not found"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request")
    })
    @PutMapping(REQUEST_ID)
    public ResponseEntity<?> update(/*@ApiParam(value = "El id del testimonio", required = true, example = "1")*/ @Valid @RequestBody TestimonialDto testimonialDto, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iTestimonialService.updateTestimonials(id, testimonialDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete a testimonial")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Testimonial deleted succesfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Testimonial not found")
    })
    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            iTestimonialService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Testimonial deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Find all testimonials")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All testimonials found succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageResponseDto.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Page doesn't exist or is out of range")
    })
    @GetMapping
    public ResponseEntity<?> getTestimonials(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageResponseDto pageResponse = iTestimonialService.getAll(page);
        return ResponseEntity.ok().body(pageResponse);
    }
}