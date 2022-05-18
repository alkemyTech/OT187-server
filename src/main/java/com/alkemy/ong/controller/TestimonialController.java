
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.PagesDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.utility.DocumentationMessages;
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
import static com.alkemy.ong.utility.DocumentationResponse.*;

import javax.validation.Valid;

@Tag(name = "Testimonials", description = "Endpoint to create, update, delete and get testimonials")
@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    private TestimonialService testimonialService;
    private MessageSource messageSource;

    @Autowired
    public TestimonialController(TestimonialService testimonialsService, MessageSource messageSource) {
        this.testimonialService = testimonialsService;
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
            return ResponseEntity.status(HttpStatus.CREATED).body(testimonialService.createTestimonial(testimonialDto));
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
            return ResponseEntity.status(HttpStatus.OK).body(testimonialService.updateTestimonials(id, testimonialDto));
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
            testimonialService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Testimonial deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Pagination of 10, role USER
    @GetMapping(params = "page")
    @Operation(summary = DocumentationMessages.TESTIMONIAL_CONTROLLER_SUMMARY_LIST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_OK,
                    description = DocumentationMessages.TESTIMONIAL_CONTROLLER_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = CODE_BAD_REQUEST,
                    description = DocumentationMessages.TESTIMONIAL_CONTROLLER_RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = CODE_FORBIDDEN,
                    description = DocumentationMessages.TESTIMONIAL_CONTROLLER_RESPONSE_403_DESCRIPTION)})
    public ResponseEntity<?> getPageTestimonial(@RequestParam(defaultValue = "0") int page) {
        PagesDto<TestimonialDto> response = testimonialService.searchPaginatedTestimonial(page);
        return ResponseEntity.ok().body(response);
    }
}