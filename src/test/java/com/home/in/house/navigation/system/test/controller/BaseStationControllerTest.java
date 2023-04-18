package com.home.in.house.navigation.system.test.controller;

import com.home.in.house.navigation.system.Application;
import static com.home.in.house.navigation.system.ITestDirectoryFileReader.resourceAsString;
import com.home.in.house.navigation.system.SecurityConfiguration;
import com.home.in.house.navigation.system.controller.BaseStationPositionController;
import com.home.in.house.navigation.system.controller.response.BaseStationReport;
import com.home.in.house.navigation.system.controller.response.BaseStationResponse;
import com.home.in.house.navigation.system.service.BaseStationPositionService;
import java.util.UUID;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = BaseStationPositionController.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={Application.class, SecurityConfiguration.class})
public class BaseStationControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BaseStationPositionService service;

    private static final String MOBILE_STATION_POSITION_URL = "/base-station/6c72fb7c-b87d-4c7f-b8b7-3bf2419df373";
    
    @Test
    public void whenGetBaseStation_thenOK() throws Exception {
        String uuid0 = "6c72fb7c-b87d-4c7f-b8b7-3bf2419df373";
        String uuid1 = "5c72fb7c-b87d-4c7f-b8b7-3bf2419df373";
        
        BaseStationReport[] reports = new BaseStationReport[2];
        BaseStationReport report0 = new BaseStationReport(UUID.fromString(uuid0), 1f);
        report0.setTimestamp(java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0"));
        reports[0] = report0;
        BaseStationReport report1 = new BaseStationReport(UUID.fromString(uuid1), 2f);
        report1.setTimestamp(java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0"));
        reports[1] = report1;
        
        BaseStationResponse response = new BaseStationResponse(
                UUID.fromString(uuid0), reports);
        
        when(service.getBaseStationReportById(any())).thenReturn(response);
        
        mockMvc.perform(get(MOBILE_STATION_POSITION_URL)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content()
                                        .json(resourceAsString("json-expected/base_station_response.json")));
    }
}
