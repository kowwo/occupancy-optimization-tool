package com.kowwo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class OccupancyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStatusEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/status"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("OK!"));
    }

    @Test
    public void testCalculateOccupancyEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/calculateOccupancy")
                        .param("economyRooms", "5")
                        .param("premiumRooms", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
