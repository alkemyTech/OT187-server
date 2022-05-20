package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    
    @Operation(summary = "Get details of a news item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details successfully obtained",
                    content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = NewsDto.class))}),
            @ApiResponse(responseCode = "403", description = "Insufficient permits to perform this action",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "News item not found",
                    content = @Content)})
    @GetMapping(REQUEST_ID)
    public ResponseEntity<NewsDto> getDetails(@PathVariable Long id) {
        NewsDto news = newsService.findById(id);
        return ResponseEntity.ok().body(news);
    }
    
    @Operation(summary = "Create a news item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News successfully Created",
                    content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = NewsDto.class))}),
            @ApiResponse(responseCode = "403", description = "Insufficient permits to perform this action",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "No data was found in the news item",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto newsDto) {
        NewsDto savedNews = newsService.save(newsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }
    
    @Operation(summary = "Update a news item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News item successfully updated",
                    content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = NewsDto.class))}),
            @ApiResponse(responseCode = "403", description = "Insufficient permits to perform this action",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "No data was found in the news item",
                    content = @Content)})
    @PutMapping(REQUEST_ID)
    public ResponseEntity<NewsDto> update(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        NewsDto updatedNews = newsService.update(newsDto, id);
        return ResponseEntity.ok(updatedNews);
    }
    
    @Operation(summary = "Delete a news item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "News item successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Insufficient permits to perform this action",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "News item not found",
                    content = @Content)})
    //Insufficient permits to perform this action
    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
