
package com.alkemy.ong;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryFullDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryService {
    private static CategoryMapper mapper;

    @BeforeAll
    public static void setUp() {
        mapper= Mappers.getMapper(CategoryMapper.class);

    }
    @Test
    void categoryDtoToCategoryEntity() {
        CategoryDto dto= new CategoryDto();
        dto.setImage("IMAGE ");
        dto.setName("NAME category");
        dto.setDescription("Description");
        Category ent= mapper.categoryDtoToCategory(dto);
        assertAll(
                ()->{
                    assertEquals(dto.getName(),ent.getName());
                    assertEquals(dto.getImage(), ent.getImage());
                    assertEquals("Description",ent.getDescription());
                }
        );
    }
    @Test
    public void categoryDtoMapperTestSimpleNull(){
        CategoryDto dto=null;
        Category entity=mapper.categoryDtoToCategory(dto);
        assertEquals(null,entity);
    }

    @Test
    void categoryToCategoryDto() {
        Category category= new Category();
        category.setId(Long.MIN_VALUE);
        category.setImage("Image Categories");
        category.setName("Name Categories");
        category.setDescription("Description");
        category.setTimestamps(LocalDateTime.now());

        CategoryDto dto=mapper.categoryToCategoryDto(category);
        assertAll(
                ()->{
                    assertEquals(dto.getName(),category.getName());
                    assertEquals(dto.getImage(), category.getImage());
                    assertEquals("Description",category.getDescription());
                }
        );
    }
    @Test
    public void categoryMapperTestSimpleNull(){
        Category entity=null;
        CategoryDto dto=mapper.categoryToCategoryDto(entity);
        assertEquals(null,dto);
    }
    @Test
    public void categoryListMapperTestSimpleNull(){
        List<Category> categoryEntityList=null;
        List<CategoryDto>categoryDtoList=mapper.listCategoryToListCategoryDto(categoryEntityList);
        assertEquals(null,categoryDtoList);
    }

    @Test
    void listCategoryEntityToListCategoryDto() {

        Category categoryList= new Category();
        categoryList.setId(Long.MAX_VALUE);
        categoryList.setImage("Image Categories");
        categoryList.setName("Category List");
        categoryList.setDescription("This is a description");
        categoryList.setTimestamps(LocalDateTime.now());
        List<Category>categoryEntityList1=new ArrayList<>(Arrays.asList(categoryList));
        List<CategoryDto>categoryDtoList=mapper.listCategoryToListCategoryDto(categoryEntityList1);

        assertAll(
                ()->{
                    assertEquals(categoryDtoList.get(0).getName(),categoryEntityList1.get(0).getName());
                    assertEquals(categoryDtoList.get(0).getImage(),categoryEntityList1.get(0).getImage());
                    assertEquals("This is a description",categoryDtoList.get(0).getDescription());
                }
        );
    }

    @Test
    void categoryToCategoryDtoFull() {
        Category cat= new Category();
        cat.setId(Long.MIN_VALUE);
        cat.setImage("IMG");
        cat.setName("NAME");
        cat.setDescription("Description");
        cat.setTimestamps(LocalDateTime.now());

        CategoryFullDto dtoFull=mapper.categoryToCategoryFullDto(cat);
        assertAll(
                ()->{
                    assertEquals(dtoFull.getName(),cat.getName());
                    assertEquals(dtoFull.getId(), cat.getId());
                    assertEquals("Description",cat.getDescription());
                }
        );
    }

    @Test
    public void categoryFullDtoMapperTestSimpleNull(){
        CategoryFullDto dtoFull=null;
        Category entity=mapper.categoryFullDtoToCategory(dtoFull);
        assertEquals(null,entity);
    }
    @Test
    public void categoryMapperToCategoryFullDtoTestSimpleNull(){
        Category entityNull=null;
        CategoryFullDto categoryFullDto;
        categoryFullDto = mapper.categoryToCategoryFullDto(entityNull);
        assertEquals(null,categoryFullDto);
    }
    @Test
    void categoryFullDtoToCategoryEntity() {
        CategoryFullDto catFullDto= new CategoryFullDto();
        catFullDto.setId(Long.MIN_VALUE);
        catFullDto.setImage("IMAGE");
        catFullDto.setName("NaMe");
        catFullDto.setSoftDelete(false);
        catFullDto.setDescription("DESCRIPTION");
        catFullDto.setTimestamps(LocalDateTime.now());

        Category entityFull=mapper.categoryFullDtoToCategory(catFullDto);
        assertAll(
                ()->{
                    assertEquals(entityFull.getName(),catFullDto.getName());
                    assertEquals(entityFull.getId(), catFullDto.getId());
                    assertEquals("DESCRIPTION",entityFull.getDescription());
                }
        );
    }
}
