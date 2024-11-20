package com.java27.blog.service;

import com.java27.blog.dto.CreatePostDTO;
import com.java27.blog.dto.PostDTO;
import com.java27.blog.entity.Post;
import com.java27.blog.entity.User;
import com.java27.blog.mapper.PostMapper;
import com.java27.blog.model.TypeUser;
import com.java27.blog.repository.PostRepository;
import com.java27.blog.repository.UserRepository;
import com.java27.blog.utils.RoleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    public PostDTO addNewPost (UserDetails userDetails, CreatePostDTO createPostDTO) throws Exception {
        String userEmail = userDetails.getUsername();
        User userAuth = userRepository.findByEmail(userEmail).orElseThrow(()-> new Exception("User not found!"));

        RoleUtils.isPermit(userAuth, List.of(TypeUser.USER));

        Post newPost = postMapper.toPost(createPostDTO, userAuth);

        postRepository.save(newPost);

        return postMapper.toPostDTO(newPost);
    }

}
