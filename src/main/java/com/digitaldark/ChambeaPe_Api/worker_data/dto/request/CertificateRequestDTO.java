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
public class CertificateRequestDTO {

    @NotBlank(message = "Certificate name is required")
    private String certificateName;

    @NotBlank(message = "Image URL is required")
    private String imgUrl;

    @NotBlank(message = "Institution name is required")
    private String institutionName;

    @NotBlank(message = "Teacher name is required")
    private String teacherName;

    @NotNull(message = "Issue date is required")
    private Timestamp issueDate;

}
