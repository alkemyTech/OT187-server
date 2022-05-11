package com.alkemy.ong.service;

import com.alkemy.ong.dto.AmazonS3ResponseDto;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideSlimDto;
import com.alkemy.ong.entity.Slide;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;
    private final AmazonS3ServiceImp amazonS3ServiceImp;



    @Override
    public SlideDto save(SlideDto slideDto)
    {
        Slide slide = slideMapper.SlideDtoToSlide(slideDto);

        if (slide.getDisposition() == null)
            slide.setDisposition(slideRepository.getLastOrder(slide.getOrganization()) + 1);

        String imageUrl = uploadImage(slideDto.getImageUrl());
        slide.setImageUrl(imageUrl);

        Slide slideSaved = slideRepository.save(slide);

        return slideMapper.SlideToSlideDto(slideSaved);
    }



    @Override
    public SlideDto update(SlideDto slideDto) throws NotFoundException
    {
        Slide slide = slideMapper.SlideDtoToSlide(slideDto);

        if (slideRepository.findById(slide.getId()).isEmpty())
            throw new NotFoundException("The slide with ID " + slide.getId() + " doest not exist.");

        if (slideDto.getImageUrl() != null) {
            String imageUrl = uploadImage(slideDto.getImageUrl());
            slide.setImageUrl(imageUrl);
        }

        Slide slideUpdated = slideRepository.save(slide);

        return slideMapper.SlideToSlideDto(slideUpdated);
    }

    @Override
    public void delete(Long id) throws NotFoundException
    {
        Optional<Slide> slide = slideRepository.findById(id);
        if (slide.isPresent())
        {
            slideRepository.deleteById(id);
            log.info("The slide with ID " + id + " was deleted.");
        }
        else
            throw new NotFoundException("The slide with ID " + id + " doest not exist.");
    }


    // La imagen se recibe en formato 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAPAAAADwCAYAAAA+VemSAAAgAEl...=='
    private String uploadImage(String encodedImage)
    {
        String data = encodedImage.split(",")[0];
        String fileName = data.substring(data.indexOf(":") + 1, data.indexOf(";")).replace('/', '.');

        String base64Image = encodedImage.split(",")[1];
        byte[] decodedImage = Base64.getDecoder().decode(base64Image);

        File file = new File(fileName);
        writeBytesToFile(file, decodedImage);

        AmazonS3ResponseDto amazonS3ResponseDto = amazonS3ServiceImp.uploadFile(file);
        return amazonS3ResponseDto.getFileURL();
    }

    private void writeBytesToFile(File file, byte[] bytes)
    {
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(bytes);
            log.info("Successfully bytes inserted");
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public SlideDto getSlideById(Long id){
        return slideMapper.SlideToSlideDto(slideRepository.findById(id).orElseThrow());
    }

    @Override
    public List<SlideSlimDto> getAllSlides(){
        return slideMapper.allSlidesToAllSlidesSlimDto(slideRepository.findAll());
    }



}
