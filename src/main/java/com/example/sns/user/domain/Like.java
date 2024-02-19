package com.example.sns.user.domain;

import com.example.sns.user.dto.LikeDTO;
import com.example.sns.user.dto.PostDTO;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Like {
    long id;
    long userId;
    long postId;

    public Like(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public LikeDTO toLikeDTO() {
        return new LikeDTO(id, userId, postId);
    }
}
