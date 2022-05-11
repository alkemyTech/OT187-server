package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.entity.Comment;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    
    @Autowired
    private final MessageSource messageSource;
    
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
    public CommentDto update(CommentDto commentDto, Long id, String token) {
        Comment comment = commentRepository.findById(id).orElseThrow(RuntimeException::new);
        comment.setBody(commentDto.getBody());
    
        return commentMapper.commentToCommentDto(comment);
    }
    
    @Override
    public void delete(Authentication aut, Long id) {
       try {
           if (checkId(aut, id)) {
               Comment entity = commentRepository.getById(id);
               
               commentRepository.save(entity);
           }
       }catch (Exception e){
           throw new NullPointerException(messageSource.getMessage("comment.not.null", null, Locale.ENGLISH));
       }
    }

    private boolean checkId(Authentication aut, Long id) {
        String username = aut.getName();
        var commentEntityOptional = commentRepository.findById(id);
        if (commentEntityOptional.isPresent()) {
            Comment comment = commentEntityOptional.get();
            String emailUserCreator = comment.getBody();
            String authorityUser = String.valueOf(aut.getAuthorities().stream().count());
            return (username.equals(emailUserCreator) || authorityUser.equals("ROLE_ADMIN"));
        } else {
            return false;
        }
    }

    @Override
    public void existId (Long id){
        if (!commentRepository.existsById(id)) {
                throw new NotFoundException(messageSource.getMessage("comment.not.found", null, Locale.ENGLISH));
        }
    }
    
}
