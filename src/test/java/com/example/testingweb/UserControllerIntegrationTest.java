package com.example.testingweb;

import java.nio.charset.Charset;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
	@Autowired
    UserController userController;

    @Autowired
    private MockMvc mockMvc;
    
    
    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String user = "{\"name\": \"TESTE\", \"email\" : \"bob@domain.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
          .content(user)
          .contentType(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content()
            .contentType(textPlainUtf8));
    }
    
    
    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
        String user = "{\"name\": \"\", \"email\" : \"bob@domain.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
          .content(user)
          .contentType(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(MockMvcResultMatchers.status().isBadRequest());
        //  .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Name is mandatory")))
         /* .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON_UTF8));*/
        }
}
