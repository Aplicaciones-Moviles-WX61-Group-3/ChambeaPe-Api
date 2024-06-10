package com.digitaldark.ChambeaPe_Api.contract.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequestDTO {
    @NotNull(message = "Worker Id cannot be null")
    @Positive(message = "Worker Id must be positive")
    private int workerId;

    @NotNull(message = "Employer Id cannot be null")
    @Positive(message = "Employer Id must be positive")
    private int employerId;

    @NotNull(message = "Start Day cannot be null")
    private Timestamp startDay;

    @NotNull(message = "End Day cannot be null")
    private Timestamp endDay;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    private Double salary;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotNull(message = "Post Id cannot be null")
    @Positive(message = "Post Id must be positive")
    private int postId;
}
