package com.java27.blog.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private String title;

    private String content;

    private Instant createAt;

}
