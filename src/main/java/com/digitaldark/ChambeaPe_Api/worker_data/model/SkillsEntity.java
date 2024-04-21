package com.digitaldark.ChambeaPe_Api.worker_data.model;

import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
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
@Table(name = "skills")
public class SkillsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "content", nullable = false, length = 25)
    private String content;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @ManyToOne
    @JoinColumn(name = "Worker_id", nullable = false,foreignKey = @ForeignKey(name = "FK_SKILLS_WORKER_ID"))
    private WorkerEntity worker;
}
