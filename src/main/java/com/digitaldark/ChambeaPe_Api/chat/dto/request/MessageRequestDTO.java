package com.digitaldark.ChambeaPe_Api.chat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDTO {
    private String content;
    private int sendById;
}
