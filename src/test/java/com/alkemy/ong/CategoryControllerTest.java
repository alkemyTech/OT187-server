package com.alkemy.ong;

import com.alkemy.ong.controller.CategoryController;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc()
@AutoConfigureJsonTesters
@SpringBootTest
class CategoryControllerTest {

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();
    @Mock
    private CategoryRepository categoryRepository;
    @MockBean
    private CategoryService categoryService;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CategoryController categoryController;
    
    
    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }
    
    @Test
    @WithMockUser(username = "userMock", authorities = "ADMIN")
    void createCategory_statusOK() throws Exception {
        CategoryDto categoryDTO=new CategoryDto();
        categoryDTO.setImage("10");
        categoryDTO.setName("Name");
        categoryDTO.setDescription("Content Valid");
        when(categoryService.findById(Long.MIN_VALUE)).thenReturn(categoryDTO);
        String content=objectWriter.writeValueAsString(categoryDTO);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "userMock", authorities = "ADMIN")
    void whenValidInput_thenReturns204() throws Exception {
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.delete("/categories/123")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest)
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "userMock", authorities = "ADMIN")
    public void testUpdateIsNotFound() {
        Throwable thrown = assertThrows(Exception.class, () -> categoryController.delete(Long.MIN_VALUE));
        assertEquals(null, thrown.getMessage());
    }
    @Test
    @WithMockUser(username = "userMock", authorities = "ADMIN")
    void TestGetCategories_ValidatePaginationOk_Role_ADMIN() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories" .concat("?page=")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "userMock", authorities = "ADMIN")
    void updateWithSuccess() throws Exception {
        Category cate = new Category();
        cate.setId(Long.MIN_VALUE);
        cate.setImage("Category Image");
        cate.setName("Category Name");
        cate.setDescription("Description");

        Category cateUpdate = new Category();
        cateUpdate.setId(Long.MIN_VALUE);
        cateUpdate.setImage("IMAGE");
        cateUpdate.setName("Category Name");
        cateUpdate.setDescription("Description");

        Mockito.when(categoryRepository.findById(cate.getId())).thenReturn(java.util.Optional.of(cate));
        Mockito.when(categoryRepository.save(cateUpdate)).thenReturn(cateUpdate);
        String updatedContent=objectWriter.writeValueAsString(cateUpdate);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.put("/categories/123456")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

}
