
package com.alkemy.ong.controller;

/*
import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.TestimonialService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alkemy.ong.utility.Constantes.*;

import javax.validation.Valid;

@RestController
@Api(value = "Testimonial controller")
@RequestMapping(TESTIMONIAL_URL)
public class TestimonialController {

    private final TestimonialService iTestimonialService;
    private final MessageSource messageSource;

    @Autowired
    public TestimonialController(TestimonialService iTestimonialService, MessageSource messageSource) {
        this.iTestimonialService = iTestimonialService;
        this.messageSource = messageSource;
    }

    @PostMapping
    @ApiOperation("Create a new testimonial")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<?> createTestimonials(@ApiParam(value = "JSON con Testimonial para crear", required = true) @Valid @RequestBody TestimonialDto testimonialDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iTestimonialService.createTestimonial(testimonialDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(REQUEST_ID)
    @ApiOperation("Update a testimonial")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 404, message = "Bad Request")
    })
    public ResponseEntity<?> update(@ApiParam(value = "El id del testimonio", required = true, example = "1") @Valid @RequestBody TestimonialDto testimonialDto, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iTestimonialService.updateTestimonials(id, testimonialDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            iTestimonialService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Testimonial deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?> getTestimonials(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageResponseDto pageResponse = iTestimonialService.getAll(page);
        return ResponseEntity.ok().body(pageResponse);
    }
}

 */