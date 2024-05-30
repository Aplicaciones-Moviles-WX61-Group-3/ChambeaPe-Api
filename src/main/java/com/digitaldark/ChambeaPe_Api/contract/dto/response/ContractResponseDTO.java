package com.digitaldark.ChambeaPe_Api.contract.dto.response;

import com.digitaldark.ChambeaPe_Api.post.dto.response.PostResponseDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

public class ContractResponseDTO {
    private int id;
    private int workerId;
    private int employerId;
    private Timestamp startDay;
    private Timestamp endDay;
    private Double salary;
    private int postId;
}
