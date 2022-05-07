
package com.alkemy.ong.controller;


import com.alkemy.ong.dto.TestimonialsCreationDto;
import com.alkemy.ong.service.TestimonialsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "Testimonial controller")
@RequestMapping("/testimonials")
public class TestimonialsController {

    private final TestimonialsService iTestimonialsService;
    private final MessageSource messageSource;

    @Autowired
    public TestimonialsController(TestimonialsService iTestimonialsService, MessageSource messageSource) {
        this.iTestimonialsService = iTestimonialsService;
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
            return ResponseEntity.status(HttpStatus.CREATED).body(iTestimonialsService.createTestimonials(testimonialsCreationDto));
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
    public ResponseEntity<?> Update(@ApiParam(value = "El id del testimonio", required = true, example = "1") @ModelAttribute(name = "testimonialsCreationDto") @Valid TestimonialsCreationDto testimonialsCreationDto, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iTestimonialsService.updateTestimonials(id, testimonialsCreationDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new HashMap<>();


        TestimonialsService testimonialsService = iTestimonialsService.getTestimonialsById(id);
        if (testimonialsService == null) {
            response.put("mensaje", "Error , no se ha encontrado el testimonio con el id indicado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        iTestimonialsService.deleteById(id);
        response.put("mensaje", "El testimonio se ha eliminado con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}