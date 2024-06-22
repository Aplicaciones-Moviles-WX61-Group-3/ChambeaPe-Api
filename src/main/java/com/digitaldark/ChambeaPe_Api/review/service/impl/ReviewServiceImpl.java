package com.digitaldark.ChambeaPe_Api.review.service.impl;

import com.digitaldark.ChambeaPe_Api.review.dto.request.ReviewRequestDTO;
import com.digitaldark.ChambeaPe_Api.review.dto.response.ReviewResponseDTO;
import com.digitaldark.ChambeaPe_Api.review.model.ReviewsEntity;
import com.digitaldark.ChambeaPe_Api.review.repository.ReviewRepository;
import com.digitaldark.ChambeaPe_Api.review.service.ReviewService;
import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.model.UsersEntity;
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
    private EmployerRepository employerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO review, int id) {

        System.out.println("id "+id);

        UsersEntity user = userRepository.findById(review.getSentById());
        if(user == null){
            throw new ValidationException("User does not exist");
        }

        ReviewsEntity reviewEntity = modelMapper.map(review, ReviewsEntity.class);

        reviewEntity.setDateCreated(dateTimeEntity.currentTime());
        reviewEntity.setDateUpdated(dateTimeEntity.currentTime());
        reviewEntity.setIsActive((byte) 1);

        if(user.getUserRole().equals("E")){
            if(!workerRepository.existsById(id)){
                throw new ValidationException("Worker does not exist");
            }

            reviewEntity.setEmployer(employerRepository.findById(review.getSentById()));
            reviewEntity.setWorker(workerRepository.findById(id));
        }
        else if(user.getUserRole().equals("W")){
            if(!employerRepository.existsById(id)){
                throw new ValidationException("Employer does not exist");
            }
            reviewEntity.setWorker(workerRepository.findById(review.getSentById()));
            reviewEntity.setEmployer(employerRepository.findById(id));
        }

        reviewRepository.save(reviewEntity);

        return modelMapper.map(reviewEntity, ReviewResponseDTO.class);
    }

    @Override
    public ReviewResponseDTO getReviewById(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new ValidationException("Review does not exist");
        }
        ReviewsEntity reviewEntity = reviewRepository.findById(id);
        return modelMapper.map(reviewEntity, ReviewResponseDTO.class);
    }

    @Override
    public ReviewResponseDTO updateReview(int id, ReviewRequestDTO review) {
        if (!reviewRepository.existsById(id)) {
            throw new ValidationException("Review does not exist");
        }
        ReviewsEntity reviewEntity = reviewRepository.findById(id);
        modelMapper.map(review, reviewEntity);

        reviewEntity.setId(id);
        reviewEntity.setDateCreated(dateTimeEntity.currentTime());
        reviewEntity.setDateUpdated(dateTimeEntity.currentTime());

        reviewRepository.save(reviewEntity);

        return modelMapper.map(reviewEntity, ReviewResponseDTO.class);
    }

    @Override
    public void deleteReviewById(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new ValidationException("Review does not exist");
        }

        reviewRepository.deleteById(id);
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
