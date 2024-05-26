package com.digitaldark.ChambeaPe_Api.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(name = "birthdate", nullable = false)
    private Timestamp birthdate;

    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "has_premium", nullable = false)
    private byte hasPremium;

    @Column(name = "profile_pic", nullable = false, length = 250)
    private String profilePic;

    @Column(name = "Date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "Date_updated", nullable = true)
    private Timestamp dateUpdated;

    @Column(name = "is_active", nullable = false)
    private byte isActive;

    @Column(name = "description", nullable = false, length = -1)
    private String description;

    @Column(name = "user_role", nullable = false, length = 1)
    private String userRole;
/**/
    @Column(name="otp", nullable = true, length = 6)
    private String otp;

    @Column(name = "otp_generated_time", nullable = true)
    private LocalDateTime otpGeneratedTime;

    @Column(name = "is_visible", nullable = false, length = 1)
    private String isVisible;
}
