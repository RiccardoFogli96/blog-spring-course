package com.java27.blog.dto;

import com.java27.blog.model.CommentDTO;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;

    private String content;

    private Instant createAt;

    private List<CommentDTO> comments;
}
