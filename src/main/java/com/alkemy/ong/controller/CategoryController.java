package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alkemy.ong.utility.Constantes.CATEGORY_URL;

@Tag(name = "Category", description = "Endpoint to create, update, delete and get page of categories")
@RestController
@RequestMapping(CATEGORY_URL)
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;


    @Operation(summary = "View a list of available categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description= "Successfully retrieved list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Iterable.class))
                    }),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> categoryDto = categoryService.findAll();
        return ResponseEntity.ok().body(categoryDto);
    }

    @Operation(summary = "Add a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody CategoryDto categoryDto){
        try {
            CategoryDto Save = categoryService.save(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(Save);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Update a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@Parameter(description = "Category id", required = true, example = "1")
                                    @PathVariable(value = "id") Long id, @RequestBody CategoryDto categorydto) {
        Map<String, Object> response = new HashMap<>();
        try {
            CategoryDto categoryUpdate = categoryService.update(id, categorydto);
            response.put("category", categoryUpdate);
            response.put("message", "Category updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "Category id", required = true, example = "1")@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            categoryService.delete(id);
            response.put("Message", "Category successfully deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Add a page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page successfully added",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Iterable.class))
                    }),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping
    public ResponseEntity<?> page(@Parameter(description = "Page number", required = true, example = "15") @RequestParam(value = "page")Integer page){
        Map<String,Object> response=new HashMap<>();

        Page<Category> categoryPage=categoryService.findAll(page);
        response.put("categories",categoryPage);
        return new ResponseEntity<>(categoryPage,HttpStatus.OK);
    }
}
