
package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.PagesDto;

public interface NewsService {

    NewsDto save(NewsDto newsDto);
    void delete(Long id);
    NewsDto findById(Long id);
    NewsDto update(NewsDto newsDto, Long id);

    PagesDto<NewsDto> getAllPagesNews(int page);
}