package com.digitaldark.ChambeaPe_Api.worker_data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementRequestDTO {

    @NotBlank(message = "Title is required")
    private String category;

    @NotBlank(message = "Title is required")
    private String text;

    @NotNull(message = "End date is required")
    private Timestamp endDate;

    @NotBlank(message = "Image URL is required")
    private String pictureUrl;

}
