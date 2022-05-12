
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.dto.CategoriesCreationDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.response.dto.CategoryResponseDto;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import java.util.Locale;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.service.CategoriesService;

//@Api(value = "Categorias controller")
@Tag(name = "Categories")
@RestController
@RequestMapping("/categories")
public class CategoriesController {

	private final CategoriesService iCategory;
	private final MessageSource message;
	@Autowired
	public CategoriesController(CategoriesService iCategory, MessageSource message) {
		this.iCategory = iCategory;
		this.message = message;
	}

	@Operation(summary = "Create a new category")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "Category saved succesfully",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = CategoryDto.class)) }),
			@ApiResponse(
					responseCode = "404",
					description = "Error while saving category"),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request")
	})
	@PostMapping
	public ResponseEntity<?> post(@Valid @ModelAttribute(name = "categoryCreationDto") CategoriesCreationDto categoryCreationDto) throws EntityNotFoundException{
		try{
			return new ResponseEntity<>(iCategory.createCategory(categoryCreationDto) ,HttpStatus.CREATED);
		}catch (EntityNotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	
        }


	@Operation(summary = "Find a category by ID")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Category found succesfully",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = CategoryDto.class)) }),
			@ApiResponse(
					responseCode = "404",
					description = "Category not found")
	})
	@GetMapping(path="/{id}")
	public ResponseEntity<?> shearch(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(iCategory.findById(id), HttpStatus.OK);
		} catch(EntityNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@Operation(summary = "Find categories by page")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Page found succesfully",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Page.class)) }),
			@ApiResponse(
					responseCode = "202",
					description = "Page not found"),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request")
	})
	@GetMapping
	public ResponseEntity<?> getAllPageable(@PageableDefault (size = 10, page = 0) Pageable pagebale, 
			@RequestParam(value = "page", defaultValue = "0") int page){
	try {
		Page<CategoryResponseDto> result = iCategory.findAllWithNameInPage(pagebale);	 		
		if(page >= result.getTotalPages() | page < 0)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(message.getMessage("pagination.error.notFound", null, Locale.getDefault()));

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}catch(NotFoundException e) {
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
        
}
        