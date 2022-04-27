package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryFullDto;
import com.alkemy.ong.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CategoryMapper {

    CategoryMapper categoryMapper= Mappers.getMapper(CategoryMapper.class);

    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto categoryToCategoryDto(Category category);

    List<CategoryDto> categoryListToCategoryDtoList(List<Category> list, boolean b);

    CategoryFullDto categoryToCategoryFullDto(Category category);

    Category categoryFullDtoToCategory(CategoryFullDto categoryFullDto);



}

