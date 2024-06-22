package com.digitaldark.ChambeaPe_Api.contract.dto.response;

import com.digitaldark.ChambeaPe_Api.post.dto.response.PostResponseDTO;

import java.sql.Timestamp;

@lombok.Data
public class ContractPoResponseDTO {
    private int id;
    private int workerId;
    private int employerId;
    private Timestamp startDay;
    private Timestamp endDay;
    private Double salary;
    private String state;
    private PostResponseDTO post;
}
