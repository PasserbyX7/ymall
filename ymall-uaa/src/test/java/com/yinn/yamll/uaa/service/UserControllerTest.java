package com.yinn.yamll.uaa.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.yinn.ymall.uaa.feign.ThirdFeignService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    ThirdFeignService thirdFeignService;

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @BeforeEach
    void init(){
        mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void queryParamTest() throws Exception {
        String username="A";
        mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("username", username))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data").value(username));
    }
    @Test
    public void queryBodyTest() throws Exception {
        String content="""
        {
            "id":1,
            "username":"A",
            "password":"1"
        }
        """;
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.username").value("A"));
    }

}