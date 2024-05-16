package com.digitaldark.ChambeaPe_Api.email.service;

import com.digitaldark.ChambeaPe_Api.user.dto.response.UserResponseDTO;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface IEmailService {
    public abstract  void sendEmail(String[] toUser, String subject, String message) throws MessagingException, IOException;
    public abstract  void userRegistered(String toUser) throws MessagingException, IOException;
    public abstract void resetPassword(String toUser) throws MessagingException, IOException;
    public abstract UserResponseDTO validateOtpInChangePassword(String email, String otp);
}
