package com.digitaldark.ChambeaPe_Api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDTO {
    //Atributes from UserEntity

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private byte hasPremium;

    private String profilePic;

    private byte isActive;

    private String description;

    //Attributes from WorkerEntity

    private String occupation;
}
