package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    CategoryDto save(CategoryDto categoryDto);
    void delete(Long id);
    CategoryDto findById(Long id);
    CategoryDto update(Long id, CategoryDto categoryDto);
    Page<Category> findAll(Integer page);
}