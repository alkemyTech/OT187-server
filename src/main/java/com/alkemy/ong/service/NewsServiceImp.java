package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.News;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsServiceImp implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public NewsDto save(NewsDto newsDto) {
        News news = newsMapper.newsDtoToNews(newsDto);
        news.setCategory(categoryRepository.findById(
                newsDto.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found")
        ));
        return newsMapper.newsToNewsDto(newsRepository.save(news));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("News not found"));
        newsRepository.softDelete(id);
    }


    @Transactional(readOnly = true)
    @Override
    public NewsDto findById(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("News not found"));
        return newsMapper.newsToNewsDto(news);
    }

    @Override
    public NewsDto update(NewsDto newsDto, Long id) {

        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("UserEntity not found"));

        news.setName(newsDto.getName());
        news.setImage(newsDto.getImage());
        news.setContent(newsDto.getContent());
        news.setCategory(categoryRepository.findById(
                newsDto.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found")
        ));

        return newsMapper.newsToNewsDto(newsRepository.save(news));

    }
}