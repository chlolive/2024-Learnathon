package com.example.sns.user.dto;



public record LikeDTO(long likeId, long userId, long postId) {

    public LikeDTO( long userId, long postId) {
        this(0, userId, postId);
    }

}