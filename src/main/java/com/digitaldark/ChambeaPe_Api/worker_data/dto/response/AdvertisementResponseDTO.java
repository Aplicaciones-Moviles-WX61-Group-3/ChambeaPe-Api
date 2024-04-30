package com.digitaldark.ChambeaPe_Api.worker_data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementResponseDTO {
    private String category;
    private String text;
    private Timestamp startDate;
    private Timestamp endDate;
    private String pictureUrl;

}
