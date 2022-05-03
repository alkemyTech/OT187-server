package com.alkemy.ong.controller;

import com.alkemy.ong.service.SlideService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.utility.Constantes.SLIDE_URL;

@RestController
@RequestMapping(SLIDE_URL)
public class SlideController {

    private SlideService slideService;

    public SlideController(SlideService slideService)
    {
        this.slideService = slideService;
    }

    
}
