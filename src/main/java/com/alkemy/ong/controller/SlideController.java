package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideSlimDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.SlideServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import java.util.List;
import static com.alkemy.ong.utility.Constantes.*;


@RestController
@RequestMapping(SLIDE_URL)
public class SlideController {

    private SlideServiceImpl slideServiceImpl;

    public SlideController(SlideServiceImpl slideServiceImpl)
    {
        this.slideServiceImpl = slideServiceImpl;
    }
/*
    @PostMapping
    public ResponseEntity<SlideDto> save(@Valid @RequestBody SlideDto slideDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(slideServiceImpl.save(slideDto));
    }

 */

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
    @GetMapping
    public ResponseEntity<List<SlideSlimDto>> getAllSlides(){
        return new ResponseEntity<>(slideServiceImpl.getAllSlides(), HttpStatus.OK);
    }

    @GetMapping(SLIDE_ID)
    public ResponseEntity<SlideDto> getSlide(@RequestParam ("id") Long id){
        if(slideServiceImpl.getSlideById(id) != null ){
            return new ResponseEntity<>(slideServiceImpl.getSlideById(id),HttpStatus.OK);
        }else return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
