package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.amazonaws.services.xray.model.Http;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.alkemy.ong.utility.Constantes.NEWS_URL;
import static com.alkemy.ong.utility.Constantes.REQUEST_ID;

@RestController
@RequestMapping(NEWS_URL)
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "Get news details by id", response = NewsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved news"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(REQUEST_ID)
    public ResponseEntity<?> getDetails(@ApiParam(value = "News id", required = true, example = "1") @PathVariable Long id) {
        try {
            NewsDto news = newsService.findById(id);
            return ResponseEntity.ok().body(news);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @ApiOperation(value = "Saves news", response = NewsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved "),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto newsDto) {
        NewsDto savedNews = newsService.save(newsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }

    @ApiOperation(value = "Updates news", response = NewsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated news"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(REQUEST_ID)
    public ResponseEntity<?> update(@ApiParam(value = "News id", required = true, example = "1") @PathVariable Long id, @RequestBody NewsDto newsDto) {
        try {
            NewsDto updatedNews = newsService.update(newsDto, id);
            return ResponseEntity.ok(updatedNews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(value = "Delete news")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<?> delete(@ApiParam(value = "News id", required = true, example = "1") @PathVariable Long id) {
        try {
            newsService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("News deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByNews(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(newsService.getAllCommentsByNews(id));
    }
}
