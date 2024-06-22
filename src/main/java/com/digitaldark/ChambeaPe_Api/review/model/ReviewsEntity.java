package com.digitaldark.ChambeaPe_Api.review.model;

import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class ReviewsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "description", nullable = false, length = -1)
    private String description;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "sent_by_id", nullable = false)
    private int sentById;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "Employer_id", nullable = false , foreignKey = @ForeignKey(name = "FK_REVIEWS_EMPLOYER_ID"))
    private EmployerEntity employer;

    @ManyToOne
    @JoinColumn(name = "Worker_id", nullable = false, foreignKey = @ForeignKey(name = "FK_REVIEWS_WORKER_ID"))
    private WorkerEntity worker;
}
