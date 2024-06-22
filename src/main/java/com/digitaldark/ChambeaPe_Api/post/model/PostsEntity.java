package com.digitaldark.ChambeaPe_Api.post.model;

import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
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
@Table(name = "posts")
public class PostsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false, length = 40)
    private String title;

    @Column(name = "description", nullable = false, length = -1)
    private String description;

    @Column(name = "subtitle", nullable = false, length = 30)
    private String subtitle;

    @Column(name = "img_url", nullable = false, length = 255)
    private String imgUrl;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @ManyToOne
    @JoinColumn(name = "Employer_id", nullable = false,foreignKey = @ForeignKey(name = "FK_POSTS_EMPLOYER_ID"))
    private EmployerEntity employer;
}