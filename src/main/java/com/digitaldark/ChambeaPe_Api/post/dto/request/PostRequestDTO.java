package com.digitaldark.ChambeaPe_Api.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {
    private String title;
    private String description;
    private String subtitle;
    private String imgUrl;
}
