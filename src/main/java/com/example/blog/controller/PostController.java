package com.example.blog.controller;

import com.example.blog.dto.PostDTO;
import com.example.blog.dto.PostRequest;
import com.example.blog.dto.UpdatePostRequest;
import com.example.blog.response.IamResponse;
import com.example.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // Инжекция сервиса через конструктор
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<IamResponse<List<PostDTO>>> getAll() {
        return ResponseEntity.ok(IamResponse.success(postService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IamResponse<PostDTO>> getById(@PathVariable Long id) {
        PostDTO post = postService.getPostById(id);
        return ResponseEntity.ok(IamResponse.success(post));
    }


    @PostMapping
    public ResponseEntity<IamResponse<PostDTO>> create(@RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(IamResponse.success(postService.createPost(postRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IamResponse<PostDTO>> update(@PathVariable Long id, @RequestBody UpdatePostRequest updateRequest) {
        return ResponseEntity.ok(IamResponse.success(postService.updatePost(id, updateRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IamResponse<Void>> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(IamResponse.success(null));
    }
}
