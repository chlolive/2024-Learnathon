package com.example.sns.user.controller;

import com.example.sns.user.dto.LikeDTO;
import com.example.sns.user.dto.PostDTO;
import com.example.sns.user.dto.SignUpDTO;
import com.example.sns.user.dto.UserDTO;
import com.example.sns.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> search(@RequestParam String name) {
        return ResponseEntity.ok(userService.search(name));
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        long userId = userService.signUp(signUpDTO);
        return ResponseEntity.created(URI.create("/users/" + userId + "/profile")).build();
    }

    @GetMapping("/{userId}/profiles")
    public ResponseEntity<UserDTO> getProfile(@PathVariable Long userId) {
        return userService.getProfile(userId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/posts")
    public ResponseEntity<PostDTO> postProfile(@PathVariable Long userId, @RequestBody PostDTO postDTO) {
        long userId_post =  userService.writePost(postDTO);
        return ResponseEntity.created(URI.create("/posts/"+userId_post)).build();
    }

    @GetMapping("/all/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(userService.getAllPosts());
    }

    @PostMapping("/add/likes")
    public ResponseEntity<LikeDTO> addLike(@RequestParam long userId, long postId ) {
        LikeDTO likeDTO = new LikeDTO(userId, postId);
        long result = userService.addLike(likeDTO);
        return ResponseEntity.created(URI.create("/likes/"+result)).build();
    }

    @GetMapping("/all/likes")
    public ResponseEntity<List<LikeDTO>> getAllLikes() {
        return ResponseEntity.ok(userService.getAllLikes());
    }

}
