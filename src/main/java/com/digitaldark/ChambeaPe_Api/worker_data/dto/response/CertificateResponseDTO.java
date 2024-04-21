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
public class CertificateResponseDTO {
    private int id;
    private String userName;
    private String certificateName;
    private String imgUrl;
    private String institutionName;
    private String teacherName;
    private Timestamp issueDate;
}
