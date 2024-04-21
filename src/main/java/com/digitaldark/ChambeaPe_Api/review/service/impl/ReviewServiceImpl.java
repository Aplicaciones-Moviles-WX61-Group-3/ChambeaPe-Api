package com.digitaldark.ChambeaPe_Api.review.service.impl;

import com.digitaldark.ChambeaPe_Api.review.dto.request.ReviewRequestDTO;
import com.digitaldark.ChambeaPe_Api.review.dto.response.ReviewResponseDTO;
import com.digitaldark.ChambeaPe_Api.review.model.ReviewsEntity;
import com.digitaldark.ChambeaPe_Api.review.repository.ReviewRepository;
import com.digitaldark.ChambeaPe_Api.review.service.ReviewService;
import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.repository.EmployerRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.UserRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.WorkerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DateTimeEntity dateTimeEntity;
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO review, int id) {

        ReviewsEntity reviewEntity = modelMapper.map(review, ReviewsEntity.class);

        reviewEntity.setDateCreated(dateTimeEntity.currentTime());
        reviewEntity.setDateUpdated(dateTimeEntity.currentTime());
        reviewEntity.setEmployer(employerRepository.findById(id));
        reviewEntity.setWorker(workerRepository.findById(id));
        reviewEntity.setIsActive((byte) 1);
        reviewRepository.save(reviewEntity);

        return modelMapper.map(reviewEntity, ReviewResponseDTO.class);

    }

    @Override
    public ReviewResponseDTO getReviewById(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new ValidationException("Review does not exist");
        }
        ReviewsEntity reviewEntity = reviewRepository.findById(id).get();
        return modelMapper.map(reviewEntity, ReviewResponseDTO.class);
    }

    @Override
    public ReviewResponseDTO updateReview(int id, ReviewRequestDTO review) {
        return null;
    }

    @Override
    public void deleteReviewById(int id) {

    }

    @Override
    public List<ReviewResponseDTO> getAllReviewsByWorkerId(int id) {
        if (!workerRepository.existsById(id)) {
            throw new ValidationException("Worker does not exist");
        }
        List<ReviewsEntity> reviewsEntity = reviewRepository.findAllByWorkerId(id);
        return reviewsEntity.stream()
                .map(review -> modelMapper.map(review, ReviewResponseDTO.class))
                .collect(Collectors.toList());

    }
}
