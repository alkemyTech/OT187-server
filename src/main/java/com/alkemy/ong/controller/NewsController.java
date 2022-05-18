package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.PagesDto;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.utility.DocumentationMessages;
import com.alkemy.ong.utility.DocumentationResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    
    @GetMapping(REQUEST_ID)
    public ResponseEntity<NewsDto> getDetails(@PathVariable Long id) {
        NewsDto news = newsService.findById(id);
        return ResponseEntity.ok().body(news);
    }
    
    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto newsDto) {
        NewsDto savedNews = newsService.save(newsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }
    
    @PutMapping(REQUEST_ID)
    public ResponseEntity<NewsDto> update(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        NewsDto updatedNews = newsService.update(newsDto, id);
        return ResponseEntity.ok(updatedNews);
    }
    
    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = DocumentationMessages.NEWS_CONTROLLER_SUMMARY_LIST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationResponse.CODE_OK,
                    description = DocumentationMessages.NEWS_CONTROLLER_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = DocumentationResponse.CODE_BAD_REQUEST,
                    description = DocumentationMessages.NEWS_CONTROLLER_RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = DocumentationResponse.CODE_FORBIDDEN,
                    description = DocumentationMessages.NEWS_CONTROLLER_RESPONSE_403_DESCRIPTION)})
    @GetMapping
    public ResponseEntity<?> getPageNews(@RequestParam(defaultValue = "0") int page){
        PagesDto<NewsDto> response = newsService.getAllPagesNews(page);
        return ResponseEntity.ok().body(response);
    }
}
