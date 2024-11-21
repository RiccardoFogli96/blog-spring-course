package com.java27.blog.mapper;

import com.java27.blog.dto.CreatePostDTO;
import com.java27.blog.dto.PostDTO;
import com.java27.blog.entity.Post;
import com.java27.blog.entity.User;
import com.java27.blog.model.CommentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    public Post toPost(CreatePostDTO createPostDTO, User user){
        return Post.builder()
                .author(user)
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .createAt(createPostDTO.getCreateAt())
                .build();
    }

    public PostDTO toDTO(Post post, List<CommentDTO> commentDTOList){
        return PostDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreateAt())
                .comments(commentDTOList)
                .build();
    }
}
