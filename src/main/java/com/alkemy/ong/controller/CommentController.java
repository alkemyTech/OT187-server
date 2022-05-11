package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.service.CommentService;
import com.alkemy.ong.utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alkemy.ong.utility.Constantes.COMMENT_URL;
import static com.alkemy.ong.utility.Constantes.REQUEST_ID;

import java.util.Locale;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping(COMMENT_URL)
public class CommentController {
    
    private final CommentService commentService;
    public CommentMapper commentMapper;
   
    public MessageSource messageSource;
    
    public final JwtUtils jwtUtils;
    
    @Autowired
    public CommentController(CommentService commentService,CommentMapper commentMapper,MessageSource messageSource, JwtUtils jwtUtils) {
        this.commentService = commentService;
        this.messageSource = messageSource;
        this.commentMapper = commentMapper;
        this.jwtUtils = jwtUtils;
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
    
    @PutMapping(REQUEST_ID)
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody CommentDto commentDto,
                                    @RequestHeader(name = "Authentication") String authHeader) {
        String token = authHeader.substring(7);
        commentService.update(commentDto, id, token);
        
        return null;
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
