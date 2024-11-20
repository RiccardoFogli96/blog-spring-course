package com.java27.blog.mapper;

import com.java27.blog.dto.InputCommentDTO;
import com.java27.blog.entity.Comment;
import com.java27.blog.entity.Post;
import com.java27.blog.entity.User;
import com.java27.blog.model.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper{

	@Mapping(target = "id", expression = "java(null)")
	@Mapping(target = "post", expression = "java(post)")
	@Mapping(target = "user", expression = "java(user)")
	Comment toComment (InputCommentDTO commentDTO, Post post, User user);

	@Mapping(target = "user", expression = "java(userDto)")
	CommentDTO toDTO (Comment comment, User user);
}
