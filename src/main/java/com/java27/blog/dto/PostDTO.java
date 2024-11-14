package com.java27.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class PostDTO {

    private String title;

    private String content;

    private Instant createAt;

}
