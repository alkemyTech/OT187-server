package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    SlideService slideService;

    @PostMapping
    public ResponseEntity<SlideDto> save(@RequestBody SlideDto slide){
        SlideDto Save = slideService.save(slide);
        return ResponseEntity.status(HttpStatus.CREATED).body(Save);
    }
    @GetMapping("/all")
    public ResponseEntity<List<SlideDto>> getAll(){
        List<SlideDto> slideDtoList = (List<SlideDto>) slideService.getAll();
        return ResponseEntity.ok().body(slideDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideDto> update(@PathVariable Long id, @RequestBody SlideDto slideDto){
        SlideDto slideDtoEdited = SlideService.editById(id, slideDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(slideDtoEdited);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        slideService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
