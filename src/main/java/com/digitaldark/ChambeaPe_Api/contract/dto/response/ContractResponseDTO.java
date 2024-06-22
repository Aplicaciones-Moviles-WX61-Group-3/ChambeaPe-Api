package com.digitaldark.ChambeaPe_Api.contract.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponseDTO {
    private int id;
    private int workerId;
    private int employerId;
    private Timestamp startDay;
    private Timestamp endDay;
    private Double salary;
    private String state;
    private int postId;
}
