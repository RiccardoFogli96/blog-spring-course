package com.java27.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java27.blog.dto.AuthenticationResponse;
import com.java27.blog.dto.CreatePostDTO;
import com.java27.blog.dto.LoginRequest;
import com.java27.blog.dto.PostDTO;
import com.java27.blog.entity.User;
import com.java27.blog.mapper.PostMapper;
import com.java27.blog.model.TypeUser;
import com.java27.blog.model.UserStatus;
import com.java27.blog.repository.PostRepository;
import com.java27.blog.repository.UserRepository;
import com.java27.blog.service.JwtService;
import com.java27.blog.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PostService postService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void addNewPost() throws Exception {
        postRepository.deleteAll();
        userRepository.deleteAll();

        User user = User.builder()
                .email("mario@gmail.com")
                .name("Mario")
                .surname("Bianchi")
                .password(passwordEncoder.encode("passwordmario"))
                .userStatus(UserStatus.ACTIVE)
                .typeUser(TypeUser.USER)
                .build();

        LoginRequest userLogin = LoginRequest.builder()
                .email("mario@gmail.com")
                .password("passwordmario")
                .build();

        userRepository.save(user);


        CreatePostDTO createPostDTO = CreatePostDTO.builder()
                .title("Buongiorno")
                .content("Signorina")
                .createAt(Instant.now())
                .build();

        MvcResult login = mockMvc.perform(post("/auth/login")
                        .content(objectMapper.writeValueAsString(userLogin))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        AuthenticationResponse authenticationResponse = objectMapper.readValue(login.getResponse().getContentAsString(), AuthenticationResponse.class);
        MvcResult mvcResult = mockMvc.perform(post("/user/post")
                .content(objectMapper.writeValueAsString(createPostDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + authenticationResponse.getToken())
        ).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        PostDTO postDTO = objectMapper.readValue(contentAsString, PostDTO.class);

        Assertions.assertNotNull(postDTO);
    }

}
