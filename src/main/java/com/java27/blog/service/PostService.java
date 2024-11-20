package com.java27.blog.service;

import com.java27.blog.dto.InputCommentDTO;
import com.java27.blog.dto.CreatePostDTO;
import com.java27.blog.dto.PostDTO;
import com.java27.blog.entity.Post;
import com.java27.blog.entity.User;
import com.java27.blog.mapper.PostMapper;
import com.java27.blog.model.TypeUser;
import com.java27.blog.repository.CommentRepository;
import com.java27.blog.repository.PostRepository;
import com.java27.blog.utils.RoleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserService userService;
    private final CommentService commentService;


    public PostDTO addNewPost (UserDetails userDetails, CreatePostDTO createPostDTO) throws Exception {
        String userEmail = userDetails.getUsername();
        User userAuth = userService.getUserByEmail(userEmail);

        RoleUtils.isPermit(userAuth, List.of(TypeUser.USER));

        Post newPost = postMapper.toPost(createPostDTO, userAuth);

        newPost.setAuthor(userAuth);

        postRepository.save(newPost);

        return postMapper.toDTO(newPost);
    }

    public PostDTO addCommentToPost(InputCommentDTO commentDTO, UserDetails userDetails, Long postId) throws Exception{

        Post post = postRepository.findById(postId).orElseThrow(() -> new Exception("No post with id " + postId));

        commentService.addComment(commentDTO, post, userDetails.getUsername());

        return ;
    }
}
