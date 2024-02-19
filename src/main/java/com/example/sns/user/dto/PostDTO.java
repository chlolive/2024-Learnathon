package com.example.sns.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record PostDTO(long postId, long userId, String content, LocalDateTime postDate) {

    public PostDTO(long userId, String content) {
        this(0, userId, content, LocalDateTime.now());
    }
}