package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    private List<CategoryDto> categoryDtoListToDtoList;

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto){
        CategoryDto Save = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Save);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> categoryDto = categoryService.getAll();
        return ResponseEntity.ok().body(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = CategoryService.editById(id, categoryDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDto1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
