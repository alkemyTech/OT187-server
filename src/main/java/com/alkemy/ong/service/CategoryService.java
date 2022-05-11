package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    CategoryDto save(CategoryDto categoryDto);
    void delete(Long id);
    CategoryDto findById(Long id);
    CategoryDto update(Long id, CategoryDto categoryDto);

}