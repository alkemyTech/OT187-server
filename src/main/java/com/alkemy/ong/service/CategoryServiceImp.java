package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;


    @Transactional
    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        categoryRepository.findById(id).orElseThrow(RuntimeException::new);
        categoryRepository.softDelete(id);
    }


    @Transactional(readOnly = true)
    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(RuntimeException::new);
        return categoryMapper.categoryToCategoryDto(category);
    }



    public CategoryDto update(Long id, CategoryDto categoryDto) {
            Category category = categoryMapper.categoryDtoToCategory(categoryDto);
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setImage(categoryDto.getImage());
            categoryRepository.save(category);
            return categoryMapper.categoryToCategoryDto(category);

    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> entities = categoryRepository.findAll();
        List<CategoryDto> result = categoryMapper.categoryListToCategoryDtoList(entities,false);
        return result;
    }

}
