package com.example.blog.mapper;

import com.example.blog.dto.*;
import com.example.blog.entity.Post;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toEntity(PostRequest request);
    PostDTO toDTO(Post post);
    void updatePostFromRequest(UpdatePostRequest request, @MappingTarget Post post);
}
