package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class NewsController {
    @Autowired
    NewsRepository newsRepository;
    
    @Autowired
    NewsMapper newsMapper;
    
    @Autowired
    NewsService newsService;
    
    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto newsDto) {
        NewsDto savedNews = newsService.save(newsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }
}
