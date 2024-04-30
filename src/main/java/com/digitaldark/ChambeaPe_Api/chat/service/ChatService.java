package com.digitaldark.ChambeaPe_Api.chat.service;

import com.digitaldark.ChambeaPe_Api.chat.dto.ChatDTO;
import com.digitaldark.ChambeaPe_Api.chat.dto.request.ChatRequestDTO;
import com.digitaldark.ChambeaPe_Api.chat.dto.request.MessageRequestDTO;
import com.digitaldark.ChambeaPe_Api.chat.dto.response.ChatResponseDTO;
import com.digitaldark.ChambeaPe_Api.chat.dto.response.MessageResponseDTO;

import java.util.List;


public interface ChatService {
    public abstract ChatResponseDTO createChat(ChatRequestDTO chatRequestDTO);
    public abstract MessageResponseDTO createMessage(MessageRequestDTO messageRequestDTO, int chatId);
    public abstract List<ChatDTO> getChatsByUserId(int id);
}
