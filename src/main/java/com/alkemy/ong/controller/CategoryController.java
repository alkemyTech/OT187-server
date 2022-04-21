package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.alkemy.ong.utility.Constantes.CATEGORY_URL;

@RestController
@RequestMapping(CATEGORY_URL)
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Category category) {
        Map<String, Object> response = new HashMap<>();
        Category category1 = Stream.of(categoryService.findById(id)).map(category2 ->
                new Category(category2.getId(), category2.getName(), category2.getDescription(),
                        category2.getImage())).findFirst().orElse(null);

        if (category1 == null) {
            response.put("error", "No se encontro ela categoria a actualizar");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        category1 = category;
        CategoryDto categoryDto=categoryService.save(Stream.of(category1).map(cat->new CategoryDto(cat.getId(),cat.getName(),cat.getDescription(),cat.getImage())).findFirst().get());
        response.put("category",categoryDto);
        response.put("mensaje","La categoria ha sido actualizada con exito");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
}
