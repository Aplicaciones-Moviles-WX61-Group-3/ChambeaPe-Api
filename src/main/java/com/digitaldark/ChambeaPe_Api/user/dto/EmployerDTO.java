package com.digitaldark.ChambeaPe_Api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDTO {

    //Atributes from UserEntity

    private int id;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @NotNull(message = "Dni cannot be null")
    @Size(min = 8, max = 8, message = "Dni must be 8 characters")
    private String dni;

    @NotNull(message = "HasPremium cannot be null")
    private byte hasPremium;

    @NotBlank(message ="Profile picture cannot be blank")
    private String profilePic;

    @NotNull(message = "Is active cannot be null")
    private byte isActive;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 1, max = 1000, message = "Description must be between 1 and 1000 characters")
    private String description;
}
