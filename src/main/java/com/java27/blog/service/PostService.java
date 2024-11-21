package com.java27.blog.service;

import com.java27.blog.dto.InputCommentDTO;
import com.java27.blog.dto.CreatePostDTO;
import com.java27.blog.dto.PostDTO;
import com.java27.blog.entity.Comment;
import com.java27.blog.entity.Post;
import com.java27.blog.entity.User;
import com.java27.blog.mapper.CommentMapper;
import com.java27.blog.mapper.PostMapper;
import com.java27.blog.model.CommentDTO;
import com.java27.blog.model.TypeUser;
import com.java27.blog.repository.PostRepository;
import com.java27.blog.utils.RoleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserService userService;
    private final CommentService commentService;
    private final InputCommentDTO inputCommentDTO;
    private final CommentMapper commentMapper;


    public PostDTO addNewPost (UserDetails userDetails, CreatePostDTO createPostDTO) throws Exception {
        String userEmail = userDetails.getUsername();
        User userAuth = userService.getUserByEmail(userEmail);

        RoleUtils.isPermit(userAuth, List.of(TypeUser.USER));

        Post newPost = postMapper.toPost(createPostDTO, userAuth);

        newPost.setAuthor(userAuth);

        postRepository.save(newPost);

        return postMapper.toDTO(newPost, new ArrayList<>());
    }

    public PostDTO addCommentToPost(InputCommentDTO commentDTO, UserDetails userDetails, Long postId) throws Exception{

        Post post = postRepository.findById(postId).orElseThrow(() -> new Exception("No post with id " + postId));

        CommentDTO commentDTO1 = commentService.addComment(commentDTO, post, userDetails.getUsername());

        List<CommentDTO> commentDTOList = new ArrayList<>();

        commentDTOList.add(commentDTO1);

        List<Comment> comments =  post.getComments();

        for(Comment c :  comments){
            CommentDTO commentDTO10 = commentMapper.toDTO(c,c.getUser());
            commentDTOList.add(commentDTO10);
        }

        return postMapper.toDTO(post, commentDTOList);
    }
}
