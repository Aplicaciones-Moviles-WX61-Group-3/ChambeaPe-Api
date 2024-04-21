package com.digitaldark.ChambeaPe_Api.postulation.service;

import com.digitaldark.ChambeaPe_Api.postulation.dto.request.PostulationRequestDTO;
import com.digitaldark.ChambeaPe_Api.postulation.dto.response.PostulationResponseDTO;

import java.util.List;

public interface PostulationService {
    public abstract List<PostulationResponseDTO> getAllPostulationsByPost(int postId);
    public abstract PostulationResponseDTO createPostulation(int postId, int workerId, PostulationRequestDTO postulation);
    public abstract void deletePostulation(int postId, int workerId);
    public abstract void updatePostulation(int id);
}
