package com.digitaldark.ChambeaPe_Api.review.service;

import com.digitaldark.ChambeaPe_Api.review.dto.request.ReviewRequestDTO;
import com.digitaldark.ChambeaPe_Api.review.dto.response.ReviewResponseDTO;
import java.util.List;

public interface ReviewService {
    public abstract ReviewResponseDTO createReview(ReviewRequestDTO review, int id);
    public abstract ReviewResponseDTO getReviewById(int id);

    public abstract ReviewResponseDTO updateReview(int id, ReviewRequestDTO review);
    public abstract void deleteReviewById(int id);

    public abstract List<ReviewResponseDTO> getAllReviewsByWorkerId(int id);
}
