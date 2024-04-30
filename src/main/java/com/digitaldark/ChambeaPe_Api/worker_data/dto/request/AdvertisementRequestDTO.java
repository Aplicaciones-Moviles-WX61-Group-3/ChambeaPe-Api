package com.digitaldark.ChambeaPe_Api.worker_data.dto.request;

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
    private String category;
    private String text;
    private Timestamp endDate;

    private String pictureUrl;

}
