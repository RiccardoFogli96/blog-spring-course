package com.java27.blog.dto;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDTO {

    private String title;

    private String content;

    private Instant createAt;
}
