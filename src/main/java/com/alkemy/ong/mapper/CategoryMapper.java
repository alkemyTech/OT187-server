package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryFullDto;
import com.alkemy.ong.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto categoryToCategoryDto(Category category);

    List<CategoryDto> listCategoryToListCategoryDto(List<Category>list);

    CategoryFullDto categoryToCategoryFullDto(Category category);

    Category categoryFullDtoToCategory(CategoryFullDto dto);


}

