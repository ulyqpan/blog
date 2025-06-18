package com.example.blog.service;

import com.example.blog.dto.*;
import com.example.blog.entity.Post;
import com.example.blog.exception.NotFoundException;
import com.example.blog.mapper.PostMapper;
import com.example.blog.repository.PostRepository;
import com.example.blog.response.IamResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repo;
    private final PostMapper mapper;

    public PostService(PostRepository repo, PostMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public IamResponse<List<PostDTO>> getAllPosts() {
        List<PostDTO> dtos = repo.findAllByIsDeletedFalse()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return IamResponse.success(dtos);
    }

    public List<PostDTO> getAll() {
        List<Post> posts = repo.findAll(); // Исправлено
        return posts.stream()
                .map(mapper::toDTO)
                .toList();
    }

    public PostDTO getPostById(Long id) {
        Post post = repo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("POST_NOT_FOUND_BY_ID"));
        return mapper.toDTO(post);
    }

    public PostDTO createPost(PostRequest request) {
        Post post = mapper.toEntity(request);
        return mapper.toDTO(repo.save(post));
    }

    public PostDTO updatePost(Long id, UpdatePostRequest request) {
        Post post = repo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("POST_NOT_FOUND_BY_ID"));
        mapper.updatePostFromRequest(request, post);
        return mapper.toDTO(repo.save(post));
    }

    public IamResponse<String> deletePost(Long id) {
        Post post = repo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("POST_NOT_FOUND_BY_ID"));
        post.setDeleted(true);
        repo.save(post);
        return IamResponse.success("Post soft-deleted");
    }
}
