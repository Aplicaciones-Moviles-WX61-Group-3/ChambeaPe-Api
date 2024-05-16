package com.digitaldark.ChambeaPe_Api.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private Timestamp birthdate;

    private String gender;

    private String profilePic;

    private String description;

    private String userRole;
}
