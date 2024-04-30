package com.digitaldark.ChambeaPe_Api.chat.repository;

import com.digitaldark.ChambeaPe_Api.chat.model.ChatEntity;
import com.digitaldark.ChambeaPe_Api.chat.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    MessageEntity findById(int id);
    List<MessageEntity> findByChatEntity(ChatEntity chatEntity);
    boolean existsById(int id);
}
