package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.News;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.mapper.NewsMapper;
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

    @Transactional
    @Override
    public NewsDto save(NewsDto newsDto) {
        News news = newsMapper.newsDtoToNews(newsDto);
        return newsMapper.newsToNewsDto(newsRepository.save(news));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        newsRepository.findById(id).orElseThrow(RuntimeException::new);
        newsRepository.softDelete(id);
    }


    @Transactional(readOnly = true)
    @Override
    public NewsDto findById(Long id) {
        News news = newsRepository.findById(id).orElseThrow(RuntimeException::new);
        return newsMapper.newsToNewsDto(news);
    }

    @Override
    public NewsDto update(NewsDto newsDto, Long id) {

        News news = newsRepository.findById(id).orElseThrow(RuntimeException::new);

        news.setName(newsDto.getName());
        news.setImage(newsDto.getImage());
        news.setImage(newsDto.getImage());
        news.setCategoryId(categoryMapper.categoryDtoToCategory(newsDto.getCategoryId()));

        return newsMapper.newsToNewsDto(newsRepository.save(news));

    }
}