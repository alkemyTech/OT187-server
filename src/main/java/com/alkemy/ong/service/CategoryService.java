package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);
    void delete(Long id);
    CategoryDto findById(Long id);
    CategoryDto update(Long id, CategoryDto categoryDto);

}