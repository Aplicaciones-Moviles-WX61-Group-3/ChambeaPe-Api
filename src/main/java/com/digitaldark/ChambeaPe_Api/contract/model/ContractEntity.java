package com.digitaldark.ChambeaPe_Api.contract.model;

import com.digitaldark.ChambeaPe_Api.post.model.PostsEntity;
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
@Table(name = "contract")
public class ContractEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "start_day", nullable = false)
    private Timestamp startDay;

    @Column(name = "end_day", nullable = false)
    private Timestamp endDay;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "Worker_id", nullable = false)
    private int workerId;

    @Column(name = "Employer_id", nullable = false)
    private int employerId;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @OneToOne
    @JoinColumn(name = "Posts_id", nullable = false,foreignKey = @ForeignKey(name = "FK_CONTRACT_POST_ID"))
    private PostsEntity post;
}
