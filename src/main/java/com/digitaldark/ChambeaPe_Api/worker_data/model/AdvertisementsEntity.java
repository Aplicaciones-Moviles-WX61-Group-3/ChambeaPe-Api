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
@Table(name = "advertisements")
public class AdvertisementsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "category", nullable = false, length = 30)
    private String category;

    @Column(name = "text", nullable = false, length = -1)
    private String text;

    @Column(name = "start_day", nullable = false)
    private Timestamp startDay;

    @Column(name = "end_day", nullable = false)
    private Timestamp endDay;

    @Column(name = "picture_url", nullable = false, length = 250)
    private String pictureUrl;

    @Column(name = "Worker_id", nullable = false,insertable=false, updatable=false)
    private int workerId;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @ManyToOne
    @JoinColumn(name = "Worker_id", nullable = false,foreignKey = @ForeignKey(name = "FK_ADVERTISEMENTS_WORKER_ID"))
    private WorkerEntity worker;
}
