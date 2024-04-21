package com.digitaldark.ChambeaPe_Api.postulation.dto.response;

import com.digitaldark.ChambeaPe_Api.user.dto.WorkerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostulationResponseDTO {
    private int id;
    private int postId;
    private WorkerDTO worker;
}
