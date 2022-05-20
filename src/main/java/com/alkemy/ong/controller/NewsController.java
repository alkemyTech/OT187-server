package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.util.List;

import static com.alkemy.ong.utility.Constantes.NEWS_URL;
import static com.alkemy.ong.utility.Constantes.REQUEST_ID;
@Tag(name = "News", description = "Endpoint to create, update, delete and get news")
@RestController
@RequestMapping(NEWS_URL)
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsService newsService;

    @Operation(summary = "Get news details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved news",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NewsDto.class))
                    }
            ),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(REQUEST_ID)
    public ResponseEntity<?> getDetails(@Parameter(description = "News id", required = true, example = "1") @PathVariable Long id) {
        try {
            NewsDto news = newsService.findById(id);
            return ResponseEntity.ok().body(news);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @Operation(summary = "Saves news")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved ",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NewsDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto newsDto) {
        NewsDto savedNews = newsService.save(newsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }

    @Operation(summary = "Updates news")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated news",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NewsDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(REQUEST_ID)
    public ResponseEntity<?> update(@Parameter(description = "News id", required = true, example = "1") @PathVariable Long id, @RequestBody NewsDto newsDto) {
        try {
            NewsDto updatedNews = newsService.update(newsDto, id);
            return ResponseEntity.ok(updatedNews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete news")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<?> delete(@Parameter(description = "News id", required = true, example = "1") @PathVariable Long id) {
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
