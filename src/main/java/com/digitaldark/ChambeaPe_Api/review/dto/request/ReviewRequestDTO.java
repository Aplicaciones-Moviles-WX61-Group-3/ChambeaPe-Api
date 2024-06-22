package com.digitaldark.ChambeaPe_Api.review.dto.request;

import java.sql.Timestamp;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 1, max = 1000, message = "Description must be between 1 and 1000 characters")
    private String description;

    @NotNull(message = "Sent by Id cannot be null")
    @Positive(message = "Sent by Id must be positive")
    private int sentById;

    @NotNull(message = "Date cannot be null")
    private Timestamp date;

    @NotNull(message = "Rating cannot be null")
    @Range(min = 1, max = 5, message = "Rating must be between 1 and 5")
    private int rating;
}
