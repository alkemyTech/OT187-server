package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.entity.Comment;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private CommentMapper commentMapper;
    @Transactional
    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        Comment savedComment = commentRepository.save(comment);
        
        return commentMapper.commentToCommentDto(savedComment);
    }
    
    @Override
    public List<CommentDto> getAllComments() {
        
        return commentMapper.commentsToCommentsDto(commentRepository.findOrderedComments());
    }
    
    @Transactional
    @Override
    public CommentDto update(CommentDto commentDto, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(RuntimeException::new);
        comment.setBody(commentDto.getBody());
        
        return commentMapper.commentToCommentDto(comment);
    }
    
}
