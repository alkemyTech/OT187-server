package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.service.CategoryService;
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

@RestController
@RequestMapping(CATEGORY_URL)
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> categoryDto = categoryService.findAll();
        return ResponseEntity.ok().body(categoryDto);
    }

    @PostMapping(value="/save")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto){
        CategoryDto Save = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Save);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Category category) {
        Map<String, Object> response = new HashMap<>();

        Category category1 = categoryMapper.categoryDtoToCategory(categoryService.findById(id));

        if (category1 == null) {
            response.put("error", "No se encontro ela categoria a actualizar");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        category1 = category;
        CategoryDto categoryDto = categoryService.save(categoryMapper.categoryToCategoryDto(category1));
        response.put("category", categoryDto);
        response.put("mensaje", "La categoria ha sido actualizada con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new HashMap<>();
        Category category = categoryMapper.categoryDtoToCategory(categoryService.findById(id));

        if (category == null) {
            response.put("error", "No se ha podido eliminar la categoria");
        }

        response.put("mensaje", "La categoria ha sido eliminada con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/page/{page}")
    public ResponseEntity<?> page(@PathVariable(value = "page")Integer page){
        Map<String,Object> response=new HashMap<>();

        Page<Category> categoryPage=categoryService.findAll(page);
        response.put("categorias",categoryPage);
        return new ResponseEntity<>(categoryPage,HttpStatus.OK);
    }
}