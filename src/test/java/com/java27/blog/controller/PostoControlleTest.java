package com.java27.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java27.blog.dto.CreatePostDTO;
import com.java27.blog.dto.PostDTO;
import com.java27.blog.mapper.PostMapper;
import com.java27.blog.repository.PostRepository;
import com.java27.blog.repository.UserRepository;
import com.java27.blog.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class PostoControlleTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addNewPost() throws Exception {
        CreatePostDTO createPostDTO = CreatePostDTO.builder()
                .title("Buongionro")
                .content("Signorina")
                .createAt(Instant.now())
                .build();

        MvcResult mvcResult = mockMvc.perform(post("/user/post")
                .content(objectMapper.writeValueAsString(createPostDTO))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        PostDTO postDTO = objectMapper.
    }
}
