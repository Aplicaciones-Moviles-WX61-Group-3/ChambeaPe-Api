package com.digitaldark.ChambeaPe_Api.chat.dto.response;

import com.digitaldark.ChambeaPe_Api.user.dto.EmployerDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.WorkerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseDTO {
    private int id;
    private Timestamp creationTime;
    private WorkerDTO worker;
    private EmployerDTO employer;
}
