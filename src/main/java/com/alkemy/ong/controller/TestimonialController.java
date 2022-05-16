
package com.alkemy.ong.controller;


import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.TestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "Testimonials", description = "Endpoint to create, update, delete and get testimonials")
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

    @Operation(summary = "Create a new testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Testimonial successfully created",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Testimonial could not be created", content = {@Content(mediaType = "text/plain")}),
    })
    @PostMapping
    public ResponseEntity<?> createTestimonials(@Parameter(description = "Dto with testimonial data") @Valid @RequestBody TestimonialDto testimonialDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iTestimonialService.createTestimonial(testimonialDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Update an existing testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Testimonial successfully updated",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Testimonial not found", content = {@Content(mediaType = "text/plain")}),
    })
    @PutMapping(REQUEST_ID)
    public ResponseEntity<?> update(@Parameter(description = "Dto with testimonial data") @Valid @RequestBody TestimonialDto testimonialDto,
                                    @Parameter(description = "Id of the testimonial to update") @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iTestimonialService.updateTestimonials(id, testimonialDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @Operation(summary = "Delete a testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Testimonial successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialDto.class))
                    }),
            @ApiResponse(responseCode = "409", description = "Testimonial not found", content = {@Content(mediaType = "text/plain")}),
    })
    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<?> delete( @Parameter(description = "Id of the testimonial to delete") @PathVariable(value = "id") Long id) {
        try {
            iTestimonialService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Testimonial deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Get a page of testimonials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponseDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Page does not exists", content = {@Content(mediaType = "text/plain")}),
    })
    @GetMapping
    public ResponseEntity<?> getTestimonials(@Parameter(description = "Id of the testimonial to delete") @RequestParam(value = "page", defaultValue = "1") int page) {
        try {
            PageResponseDto pageResponse = iTestimonialService.getAll(page);
            return ResponseEntity.ok().body(pageResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}