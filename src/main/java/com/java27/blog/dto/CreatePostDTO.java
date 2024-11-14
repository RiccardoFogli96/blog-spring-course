package com.java27.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class CreatePostDTO {

    private String title;

    private String content;

    private Instant createAt;
}
