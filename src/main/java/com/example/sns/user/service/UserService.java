package com.example.sns.user.service;

import com.example.sns.user.dao.UserMapper;
import com.example.sns.user.domain.Like;
import com.example.sns.user.domain.Post;
import com.example.sns.user.domain.User;
import com.example.sns.user.dto.LikeDTO;
import com.example.sns.user.dto.PostDTO;
import com.example.sns.user.dto.SignUpDTO;
import com.example.sns.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userMapper.findAll().stream().map(User::toDTO).toList();
    }

    public long signUp(SignUpDTO signUpDTO) {
        return userMapper.insert(new User(signUpDTO.userName(), signUpDTO.email(),
                passwordEncoder.encode(signUpDTO.password())));
    }

    public List<UserDTO> search(String name) {
        return userMapper.search(name).stream().map(User::toDTO).toList();
    }

    public Optional<UserDTO> getProfile(long userId) {
        return Optional.ofNullable(userMapper.findById(userId)).map(User::toDTO);
    }


    public long writePost(PostDTO postDTO){
        return userMapper.insertPost(new Post(postDTO.userId(), postDTO.content()));
    }

    public List<PostDTO> getAllPosts() {
        return userMapper.findAllPost().stream().map(Post::toPostDTO).toList();
    }

    public long addLike(LikeDTO likeDTO) {
        return userMapper.insertLike(new Like(likeDTO.userId(),likeDTO.postId()));
    }

    public List<LikeDTO> getAllLikes(){
        return userMapper.findAllLike().stream().map(Like::toLikeDTO).toList();
    }
}
