package com.com.blog.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 글 한건 쓰기 PostDto
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private String title;
    private String content;
}
