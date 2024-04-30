package com.digitaldark.ChambeaPe_Api.chat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "time", nullable = false)
    private Timestamp time;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "send_by_id", nullable = false)
    private int sendById;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @ManyToOne
    @JoinColumn(name = "Chat_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CHAT_ID"))
    private ChatEntity chatEntity;
}
