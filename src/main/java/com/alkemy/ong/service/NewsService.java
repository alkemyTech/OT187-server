
package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.NewsDto;

import java.util.List;

public interface NewsService {

    NewsDto save(NewsDto newsDto);
    void delete(Long id);
    NewsDto findById(Long id);
    NewsDto update(NewsDto newsDto, Long id);
    List<CommentDto> getAllCommentsByNews(Long id);
}