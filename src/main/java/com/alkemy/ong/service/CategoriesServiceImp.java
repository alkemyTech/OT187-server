package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoriesCreationDto;
import com.alkemy.ong.response.dto.CategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoriesServiceImp implements CategoriesService {

    @Override
    public CategoryResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return null;
    }

    @Override
    public CategoryResponseDto createCategory(CategoriesCreationDto category) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }

    @Override
    public Locale.Category findCategoriesById(Long id) {
        return null;
    }

    @Override
    public Page<CategoryResponseDto> findAllWithNameInPage(Pageable pageable) {
        return null;
    }

    @Override
    public CategoryResponseDto updateCategoryById(Long id, CategoriesCreationDto dto) {
        return null;
    }
}
