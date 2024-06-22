package com.digitaldark.ChambeaPe_Api.postulation.dto.response;

import com.digitaldark.ChambeaPe_Api.post.dto.response.PostResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostulationWorkerResponseDTO {
    private int id;
    private PostResponseDTO post;
}
