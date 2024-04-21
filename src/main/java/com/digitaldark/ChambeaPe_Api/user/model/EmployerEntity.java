package com.digitaldark.ChambeaPe_Api.user.model;

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
@Table(name = "employer")
public class EmployerEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @OneToOne
    @JoinColumn(name = "User_id", nullable = false,foreignKey = @ForeignKey(name = "FK_EMPLOYER_USER_ID"))
    private UsersEntity user;
}
