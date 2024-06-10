package com.digitaldark.ChambeaPe_Api.post.service;

import com.digitaldark.ChambeaPe_Api.post.dto.request.PostRequestDTO;
import com.digitaldark.ChambeaPe_Api.post.dto.response.PostResponseDTO;

import java.util.List;

public interface PostService {
    public abstract PostResponseDTO getPostById(int id);
    public abstract List<PostResponseDTO> getAllPosts();
    public abstract List<PostResponseDTO> getAllPostsByEmployerId(int employerId);
    public abstract PostResponseDTO createPost(PostRequestDTO post, int workId);
    public abstract PostResponseDTO updatePost(int id, PostRequestDTO post);
    public abstract void deletePost(int id);
}
