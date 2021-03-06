package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.service.CommentService;
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

    @Autowired
    private final CommentService commentService;

    @Autowired
    public MessageSource messageSource;

    @Autowired
    public CommentController(CommentService commentService, CommentMapper commentMapper, MessageSource messageSource) {
        this.commentService = commentService;
        this.messageSource = messageSource;
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
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CommentDto commentDto,
                                    @RequestHeader(name = "Authorization") String authHeader) {
        String token = authHeader.substring(7);

        try {
            commentService.update(commentDto, id, token);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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
