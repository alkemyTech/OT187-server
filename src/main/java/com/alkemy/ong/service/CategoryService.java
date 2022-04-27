package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.entity.Category;

import java.util.List;

public interface CategoryService {

    static CategoryDto editById(Long id, CategoryDto categoryDto) {
        return categoryDto;
    }

    CategoryDto save(CategoryDto categoryDto);
    void delete(Long id);
    CategoryDto findById(Long id);
    CategoryDto update(Long id, CategoryDto categoryDto);

    List<CategoryDto> getAll();
}