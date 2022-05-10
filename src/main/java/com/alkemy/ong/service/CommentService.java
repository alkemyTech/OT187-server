package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.entity.Comment;

import java.util.List;

public interface CommentService {
    
    CommentDto save(CommentDto commentDto);
    List<CommentDto> getAllComments();
    CommentDto update(CommentDto commentDto, Long id);
}
