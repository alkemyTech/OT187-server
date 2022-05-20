package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.entity.Comment;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.utility.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsRepository newsRepository;

    
    @Autowired
    JwtUtils jwtUtils;
    
    @Transactional
    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        comment.setNews(newsRepository.findById(commentDto.getNewsId()).orElseThrow(() -> new NotFoundException("News not found")));
        comment.setUser(userRepository.findById(commentDto.getUserId()).orElseThrow(() -> new NotFoundException("User not found")));

        Comment savedComment = commentRepository.save(comment);

        commentDto.setNewsId(savedComment.getNews().getId());
        commentDto.setUserId(savedComment.getUser().getId());
        commentDto.setId(savedComment.getId());

        return commentDto;
    }
    
    @Override
    public List<CommentDto> getAllComments() {
        
        return commentMapper.commentsToCommentsDto(commentRepository.findOrderedComments());
    }
    
    @Transactional
    @Override
    public CommentDto update(CommentDto commentDto, Long id, String token) throws Exception{
        Comment comment = commentRepository.findById(id).orElseThrow(()->new NotFoundException("Comment not found"));
        
        String email = jwtUtils.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
            Integer userId = user.getId();
            String userRole = user.getRoleId().getName();
    
        if (userRole.equals("ADMIN")) {
            comment.setBody(commentDto.getBody());
            return commentMapper.commentToCommentDto(comment);
        } else {
            if (!userId.equals(comment.getUser().getId())) {
                throw new Exception();
            } else {
                comment.setBody(commentDto.getBody());
                return commentMapper.commentToCommentDto(comment);
            }
        }
    }
    
    @Override
    public void delete(Authentication aut, Long id) {
       try {
           if (checkId(aut, id)) {
               commentRepository.softDelete(id);
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
