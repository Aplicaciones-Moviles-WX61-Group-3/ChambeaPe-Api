package com.digitaldark.ChambeaPe_Api.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @NotNull(message = "Birthdate cannot be null")
    private Timestamp birthdate;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @NotBlank(message = "DNI cannot be blank")
    @Size(min = 8, max = 8, message = "DNI must be 8 characters")
    private String dni;

    @NotBlank(message = "Profile picture cannot be blank")
    private String profilePic;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "User role cannot be blank")
    private String userRole;
}
