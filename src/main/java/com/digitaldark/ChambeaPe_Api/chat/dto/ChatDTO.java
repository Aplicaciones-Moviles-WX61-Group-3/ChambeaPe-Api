package com.digitaldark.ChambeaPe_Api.chat.dto;

import com.digitaldark.ChambeaPe_Api.chat.dto.response.MessageResponseDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private int id;
    private UserResponseDTO user;
    private List<MessageResponseDTO> messages;
}
