package com.java27.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java27.blog.dto.RegisterRequest;
import com.java27.blog.entity.User;
import com.java27.blog.mapper.UserMapper;
import com.java27.blog.repository.UserRepository;
import com.java27.blog.service.AuthenticationService;
import com.java27.blog.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  JwtService jwtService;

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
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("No user found"));

        Assertions.assertEquals(user.getPassword(),passwordEncoder.encode(request.getPassword()));
    }

}
