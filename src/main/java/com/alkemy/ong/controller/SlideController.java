package com.alkemy.ong.controller;

import com.alkemy.ong.service.SlideServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.utility.Constantes.SLIDE_URL;

@RestController
@RequestMapping(SLIDE_URL)
public class SlideController {

    private SlideServiceImpl slideServiceImpl;

    public SlideController(SlideServiceImpl slideServiceImpl)
    {
        this.slideServiceImpl = slideServiceImpl;
    }

    
}
