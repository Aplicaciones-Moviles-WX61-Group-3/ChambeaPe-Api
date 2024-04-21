package com.digitaldark.ChambeaPe_Api.review.dto.request;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    private String description;
    private int sentById;
    private Timestamp date;
    private int rating;


}
