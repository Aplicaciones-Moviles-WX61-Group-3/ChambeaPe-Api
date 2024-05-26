package com.digitaldark.ChambeaPe_Api.review.controller;

import com.digitaldark.ChambeaPe_Api.review.dto.request.ReviewRequestDTO;
import com.digitaldark.ChambeaPe_Api.review.dto.response.ReviewResponseDTO;
import com.digitaldark.ChambeaPe_Api.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ReviewController", description = "Controller to handle Review")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") // Puerto de Angular

public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "Get all reviews by worker id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all reviews by worker id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/workers/{id}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviewsByWorkerId(@PathVariable(value = "id") int id) {
        return new ResponseEntity<List<ReviewResponseDTO>>(reviewService.getAllReviewsByWorkerId(id), HttpStatus.OK);
    }

    @Operation(summary = "Create review by worker id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning created review",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResponseDTO.class)))
    @Transactional
    @PostMapping("/workers/{id}/reviews")
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO review, @PathVariable(value = "id") int id) {
        return new ResponseEntity<ReviewResponseDTO>(reviewService.createReview(review, id), HttpStatus.CREATED);
    }

    @Operation(summary = "Update review by id")
    @ApiResponse(responseCode = "200",
            description = "Successful operation, returning updated review",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResponseDTO.class)))
    @Transactional
    @PutMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable(value = "id") int id,@Valid @RequestBody ReviewRequestDTO review) {
        return new ResponseEntity<ReviewResponseDTO>(reviewService.updateReview(id, review), HttpStatus.OK);
    }

    @Operation(summary = "Delete review by id")
    @ApiResponse(responseCode = "200",
            description = "Successful operation, returning void")
    @Transactional
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable(value = "id") int id) {
        reviewService.deleteReviewById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}