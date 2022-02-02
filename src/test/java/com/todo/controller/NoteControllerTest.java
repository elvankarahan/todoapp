package com.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.controllers.NoteController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NoteController.class)
@WithMockUser
public class NoteControllerTest {

    @InjectMocks
    NoteController noteController;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    public void should_list_Notes() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/notes").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    }

}

