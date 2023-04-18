package com.home.in.house.navigation.system.test.controller;

import com.home.in.house.navigation.system.Application;
import com.home.in.house.navigation.system.controller.response.MobileStationPositionResponse;
import com.home.in.house.navigation.system.ITestDirectoryFileReader;
import static com.home.in.house.navigation.system.ITestDirectoryFileReader.resourceAsString;
import com.home.in.house.navigation.system.SecurityConfiguration;
import com.home.in.house.navigation.system.controller.MobileStationPositionController;
import com.home.in.house.navigation.system.service.MobileStationPositionService;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = MobileStationPositionController.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={Application.class, SecurityConfiguration.class})
public class MobileStationPositionControllerTest implements ITestDirectoryFileReader {    
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MobileStationPositionService service;

    private static final String MOBILE_STATION_POSITION_URL = "/location/6c72fb7c-b87d-4c7f-b8b7-3bf2419df373";
    
    @Test
    public void whenGetMobileStationPosition_thenOK() throws Exception {
        MobileStationPositionResponse response = new MobileStationPositionResponse(
                UUID.fromString("6c72fb7c-b87d-4c7f-b8b7-3bf2419df373"), 1f, 2f);
        response.setError_code(100);
        response.setError_radius(10f);
        response.setError_description("description");
        
        when(service.getMobileStationPositionById(any())).thenReturn(response);
        
        mockMvc.perform(get(MOBILE_STATION_POSITION_URL)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content()
                                        .json(resourceAsString("json-expected/mobile_station_position_response.json")));
    }
}
