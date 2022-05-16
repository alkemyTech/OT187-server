package com.alkemy.ong;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static com.alkemy.ong.utility.Constantes.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ActivityController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityService activityService;

    @Autowired
    private JacksonTester<ActivityDto> json;

    private ActivityDto activityDto;

    @BeforeEach
    void setUp() {
        activityDto = new ActivityDto();
        activityDto.setName("Activity");
        activityDto.setContent("Activity Content");
        activityDto.setImage("activity.image");
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void createActivityWithAdminRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ACTIVITY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isCreated());

        verify(activityService, times(1)).createActivity(activityDto);

    }

    @Test
    @WithMockUser(authorities = "USER")
    void notCreateActivityWithUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ACTIVITY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isForbidden());

        verify(activityService, times(0)).createActivity(activityDto);

    }

    @Test
    void notCreateActivityWithoutRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ACTIVITY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isForbidden());

        verify(activityService, times(0)).createActivity(activityDto);

    }


    @Test
    @WithMockUser(authorities = "ADMIN")
    void notCreateActivityWithoutData() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ACTIVITY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(new ActivityDto())))
                .andExpect(status().isBadRequest());

        verify(activityService, times(0)).createActivity(activityDto);

    }


    @Test
    @WithMockUser(authorities = "ADMIN")
    void getAllActiveActivitiesWithAdminRole() throws Exception {

        this.mockMvc.perform(get(ACTIVITY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isOk());

        verify(activityService, times(1)).getAllActivities();
    }

    @Test
    @WithMockUser(authorities = "USER")
    void getAllActiveActivitiesWithUserRole() throws Exception {

        this.mockMvc.perform(get(ACTIVITY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isOk());

        verify(activityService, times(1)).getAllActivities();
    }

    @Test
    void notGetAllActiveActivitiesWithoutRole() throws Exception {

        this.mockMvc.perform(get(ACTIVITY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isForbidden());

        verify(activityService, times(0)).getAllActivities();
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void updateActivityWithAdminRole() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put(ACTIVITY_URL + REQUEST_ID, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isOk());

        verify(activityService, times(1)).updateActivity(1L, activityDto);
    }

    @Test
    @WithMockUser(authorities = "USER")
    void updateActivityWithUserRole() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put(ACTIVITY_URL + REQUEST_ID, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isForbidden());

        verify(activityService, times(0)).updateActivity(1L, activityDto);
    }

    @Test
    void updateActivityWithoutRole() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put(ACTIVITY_URL + REQUEST_ID, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isForbidden());

        verify(activityService, times(0)).updateActivity(1L, activityDto);
    }


    @Test
    @WithMockUser(authorities = "ADMIN")
    void notUpdateActivityWithNonExistentId() throws Exception {

        when(activityService.updateActivity(2L, activityDto)).thenThrow(new NotFoundException("Activity with id 2 not found"));

        mockMvc.perform(MockMvcRequestBuilders
                        .put(ACTIVITY_URL + REQUEST_ID, 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(activityDto)))
                .andExpect(status().isNotFound());

        verify(activityService, times(1)).updateActivity(2L, activityDto);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void notUpdateActivityWithNullData() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put(ACTIVITY_URL + REQUEST_ID, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(new ActivityDto())))
                .andExpect(status().isBadRequest());

        verify(activityService, times(0)).updateActivity(1L, new ActivityDto());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void deleteActivityWithAdminRole() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(ACTIVITY_URL + REQUEST_ID, 1))
                .andExpect(status().isNoContent());

        verify(activityService, times(1)).deleteActivity(1L);
    }

    @Test
    @WithMockUser(authorities = "USER")
    void deleteActivityWithUserRole() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(ACTIVITY_URL + REQUEST_ID, 1))
                .andExpect(status().isForbidden());

        verify(activityService, times(0)).deleteActivity(1L);
    }

    @Test
    void deleteActivityWithoutRole() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(ACTIVITY_URL + REQUEST_ID, 1))
                .andExpect(status().isForbidden());

        verify(activityService, times(0)).deleteActivity(1L);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void notDeleteActivityWithNonExistentId() throws Exception {

        doThrow(new NotFoundException("")).when(activityService).deleteActivity(2L);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(ACTIVITY_URL + REQUEST_ID, 2))
                .andExpect(status().isNotFound());

        verify(activityService, times(1)).deleteActivity(2L);
    }

    private String dtoToJson(ActivityDto dto) throws IOException {
        return json.write(dto).getJson();
    }
}
