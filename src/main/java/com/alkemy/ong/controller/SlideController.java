package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.SlideServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.utility.Constantes.REQUEST_ID;
import static com.alkemy.ong.utility.Constantes.SLIDE_URL;

@RestController
@RequestMapping(SLIDE_URL)
public class SlideController {

    private SlideServiceImpl slideServiceImpl;

    public SlideController(SlideServiceImpl slideServiceImpl)
    {
        this.slideServiceImpl = slideServiceImpl;
    }

    @PostMapping
    public ResponseEntity<SlideDto> save(@Valid @RequestBody SlideDto slideDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(slideServiceImpl.save(slideDto));
    }

    @PutMapping
    public ResponseEntity<SlideDto> update(@Valid @RequestBody SlideDto slideDto) throws NotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(slideServiceImpl.update(slideDto));
    }

    @DeleteMapping(REQUEST_ID)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) throws NotFoundException
    {
        slideServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
