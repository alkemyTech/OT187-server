
package com.alkemy.ong.controller;
/*
import com.alkemy.ong.dto.CategoriesCreationDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.response.dto.CategoryResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Locale;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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

@Api(value = "Categorias controller")
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

	@ApiOperation("Creation Categories")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful Operation"),
			@ApiResponse(code = 404, message = "Not Found")
	})
	@PostMapping
	public ResponseEntity<?> post(@Valid @ModelAttribute(name = "categoryCreationDto") CategoriesCreationDto categoryCreationDto) throws EntityNotFoundException{
		try{
			return new ResponseEntity<>(iCategory.createCategory(categoryCreationDto) ,HttpStatus.CREATED);
		}catch (EntityNotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	
        }
        

@ApiOperation("Category details")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful Operation"),
			@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(path="/{id}")
	public ResponseEntity<?> shearch(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(iCategory.findById(id), HttpStatus.OK);
		} catch(EntityNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@ApiOperation("Category list")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful Operation"),
			@ApiResponse(code = 404, message = "Not Foundt")
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

 */
        