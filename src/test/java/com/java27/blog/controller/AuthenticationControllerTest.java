package com.java27.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java27.blog.config.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AuthenticationControllerTest.class)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
   /* @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    */

    public ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void register_WhenAllOk() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .email("mario@gmail.com")
                .name("mario")
                .surname("mario")
                .password("passwordsicura").build();

        MvcResult result = mockMvc.perform(post("/auth/register-user")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
      //  User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("No user found"));

        //Assertions.assertEquals(user.getPassword(),passwordEncoder.encode(request.getPassword()));
    }

}
