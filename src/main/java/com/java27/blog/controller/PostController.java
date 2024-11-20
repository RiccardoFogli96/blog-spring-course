package com.java27.blog.controller;

import com.java27.blog.dto.CommentDTO;
import com.java27.blog.dto.CreatePostDTO;
import com.java27.blog.dto.PostDTO;
import com.java27.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostDTO> addNewPost (@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreatePostDTO createPostDTO) throws Exception {
        return ResponseEntity.ok(postService.addNewPost(userDetails, createPostDTO));
    }

    @PostMapping("/post/{post_id}/comment/")
    public ResponseEntity<?> addCommentToPost(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(postService.addCommentToPost(commentDTO));
    }
}
