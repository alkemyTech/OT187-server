package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.alkemy.ong.utility.Constantes.COMMENT_URL;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(COMMENT_URL)
public class CommentController {
    
    @Autowired
    private final CommentService commentService;
    public CommentMapper commentMapper;
   
    public MessageSource messageSource;
    
    
    public CommentController(CommentService commentService,CommentMapper comentMapper,MessageSource messageSource) {
        this.commentService = commentService;
        this.messageSource = messageSource;
        this.commentMapper = commentMapper;
    }
    
    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments() {
        List<CommentDto> comments = commentService.getAllComments();
        
        return ResponseEntity.ok().body(comments);
    }
    
    
      @PostMapping
    public ResponseEntity<?> addNewComment(@Valid @RequestBody CommentDto commentDto) {
            CommentDto commentSaved = commentService.save(commentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentSaved);
    }
    
    
      @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Authentication aut, @PathVariable Long id) {
        commentService.existId(id);
        try {
            commentService.delete(aut, id);
            return ResponseEntity.ok(messageSource.getMessage("comment.delete.successfully", null, Locale.ENGLISH));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
