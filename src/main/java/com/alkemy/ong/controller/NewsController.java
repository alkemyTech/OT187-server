package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.utility.Constantes.NEWS_URL;
import static com.alkemy.ong.utility.Constantes.REQUEST_ID;

@Tag(name = "News")
@RestController
@RequestMapping(NEWS_URL)
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;
    
    @Autowired
    private NewsMapper newsMapper;
    
    @Autowired
    private NewsService newsService;

    @Operation(summary = "Find a news by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "News found succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsDto.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "News not found")
    })
    @GetMapping(REQUEST_ID)
    public ResponseEntity<NewsDto> getDetails(@PathVariable Long id) {
        NewsDto news = newsService.findById(id);
        return ResponseEntity.ok().body(news);
    }

    @Operation(summary = "Save a new news")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "News saved succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsDto.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto newsDto) {
        NewsDto savedNews = newsService.save(newsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }

    @Operation(summary = "Update a news")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "News updated succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsDto.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category/User not found")
    })
    @PutMapping(REQUEST_ID)
    public ResponseEntity<NewsDto> update(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        NewsDto updatedNews = newsService.update(newsDto, id);
        return ResponseEntity.ok(updatedNews);
    }

    @Operation(summary = "Delete a news")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "News deleted succesfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "News not found")
    })
    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
