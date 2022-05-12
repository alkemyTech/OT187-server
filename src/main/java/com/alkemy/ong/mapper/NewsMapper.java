package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.entity.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    News newsDtoToNews(NewsDto newsDto);
    NewsDto newsToNewsDto(News news);

    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);
}