package com.example.sns.user.domain;

import com.example.sns.user.dto.PostDTO;
import com.example.sns.user.dto.UserDTO;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;


@Data
@Getter
public class Post {
    long id;
    long userId;
    String content;
    LocalDateTime postDate;

    public Post(long userId, String content) {
        this.userId = userId;
        this.content = content;
        this.postDate = LocalDateTime.now();
    }

    public PostDTO toPostDTO() {
        return new PostDTO(id, userId, content, postDate);
    }
}
