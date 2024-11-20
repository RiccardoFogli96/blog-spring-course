package com.java27.blog.service;

import com.java27.blog.dto.InputCommentDTO;
import com.java27.blog.entity.Comment;
import com.java27.blog.entity.Post;
import com.java27.blog.entity.User;
import com.java27.blog.mapper.CommentMapper;
import com.java27.blog.model.CommentDTO;
import com.java27.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService{

	private final CommentMapper commentMapper;
	private final CommentRepository commentRepository;
	private final UserService userService;

	public CommentDTO addComment(InputCommentDTO inputCommentDTO, Post post, String email) throws Exception{

		User logged = userService.getUserByEmail(email);

		Comment comment = commentMapper.toComment(inputCommentDTO, post, logged);

		commentRepository.save(comment);
		return commentMapper.toDTO(comment, logged);
	}

}
