package com.digitaldark.ChambeaPe_Api.postulation.model;

import com.digitaldark.ChambeaPe_Api.post.model.PostsEntity;
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
@Table(name = "postulations")
public class PostulationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @Column(name = "is_accepted", nullable = false)
    private byte isAccepted;

    @ManyToOne
    @JoinColumn(name = "Posts_id", nullable = false,foreignKey = @ForeignKey(name = "FK_POSTULATIONS_POST_ID"))
    private PostsEntity post;

    @ManyToOne
    @JoinColumn(name = "Worker_id", nullable = false,foreignKey = @ForeignKey(name = "FK_POSTULATIONS_EMPLOYER_ID"))
    private WorkerEntity worker;
}
