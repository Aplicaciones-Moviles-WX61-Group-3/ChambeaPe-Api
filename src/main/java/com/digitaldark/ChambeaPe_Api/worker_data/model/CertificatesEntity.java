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
@Table(name = "certificates")
public class CertificatesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_name", nullable = false, length = 60)
    private String userName;

    @Column(name = "img_url", nullable = false, length = 250)
    private String imgUrl;

    @Column(name = "institution_name", nullable = false, length = 60)
    private String institutionName;

    @Column(name = "teacher_name", nullable = false, length = 40)
    private String teacherName;

    @Column(name = "issue_date", nullable = false)
    private Timestamp issueDate;

    @Column(name = "certificate_name", nullable = false, length = 60)
    private String certificateName;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @ManyToOne
    @JoinColumn(name = "Worker_id", nullable = false,foreignKey = @ForeignKey(name = "FK_CERTICATES_WORKER_ID"))
    private WorkerEntity worker;
}
