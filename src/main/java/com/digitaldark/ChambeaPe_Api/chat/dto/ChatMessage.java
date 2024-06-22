package com.digitaldark.ChambeaPe_Api.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 5000, message = "Message must be at most 5000 characters")
    String message;

    @NotBlank(message = "User cannot be blank")
    String user;

    @NotBlank(message = "Timestamp cannot be blank")
    String timestamp;
}
