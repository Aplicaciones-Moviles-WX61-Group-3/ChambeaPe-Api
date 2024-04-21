package com.digitaldark.ChambeaPe_Api.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    private int id;
    private String title;
    private String description;
    private String subtitle;
    private String imgUrl;
    private int employerId;
}
