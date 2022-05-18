package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);
    NewsDto newsToNewsDto(News news);
    News newsDtoToNews(NewsDto newsDto);
    List<NewsDto> listNewsEntityToNewsDto(List<News> news);
}