package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.entity.Comment;

import java.util.List;
import org.springframework.security.core.Authentication;

public interface CommentService {
    
    CommentDto save(CommentDto commentDto);
    List<CommentDto> getAllComments();
    CommentDto update(CommentDto commentDto, Long id);
    
     void delete(Authentication aut, Long id);

    void existId(Long id);
}
