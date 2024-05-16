package com.digitaldark.ChambeaPe_Api.user.service;

import com.digitaldark.ChambeaPe_Api.user.dto.request.UserLoginDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.request.UserRequestDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.response.UserResponseDTO;
import com.digitaldark.ChambeaPe_Api.user.model.UsersEntity;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public abstract UserResponseDTO getUser(int id);
    public abstract UsersEntity createUser(UsersEntity user) throws MessagingException, IOException;
    public abstract UserResponseDTO createUserDTO(UserRequestDTO user) throws MessagingException, IOException;
    public abstract UserResponseDTO getUserEmailAndPass(UserLoginDTO userLoginDTO);
    public abstract List<UserResponseDTO> getAllUsers();
    public abstract void deleteUser(int id);
    public abstract void updateUser(int id, UsersEntity user);
}
