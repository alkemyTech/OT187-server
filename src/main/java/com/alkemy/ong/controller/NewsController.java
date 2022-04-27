package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class NewsController {
    @Autowired
    NewsRepository newsRepository;
    
    @Autowired
    NewsMapper newsMapper;
    
    @Autowired
    NewsService newsService;
    
    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getDetails(@PathVariable Long id) {
        NewsDto news = newsService.findById(id);
        return ResponseEntity.ok().body(news);
    }
    
    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto newsDto) {
        NewsDto savedNews = newsService.save(newsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> update(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        NewsDto updatedNews = newsService.update(newsDto, id);
        return ResponseEntity.ok(updatedNews);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
