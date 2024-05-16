package com.digitaldark.ChambeaPe_Api.email.controller;

import com.digitaldark.ChambeaPe_Api.email.dto.EmailDTO;
import com.digitaldark.ChambeaPe_Api.email.service.IEmailService;
import com.digitaldark.ChambeaPe_Api.user.dto.response.UserResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/emails/sendMessage")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDTO emailDto){

        System.out.println("Mensaje Recibido " + emailDto);

        emailService.sendEmail(emailDto.getToUser(),emailDto.getSubject(), emailDto.getMessage());

        Map<String,String> response = new HashMap<>();
        response.put("Estado", "Correo enviado");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/emails/generateOtpCode")
    public ResponseEntity<?> receiveRequestChangePassword(String email) throws MessagingException {

        System.out.println("Mensaje Recibido " + email);

        emailService.generateCodeOtp(email);

        Map<String,String> response = new HashMap<>();
        response.put("Estado", "Codigo de verificación enviado a su correo");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/emails/validateOtp")
    public ResponseEntity<UserResponseDTO> validateOtp(String email, String otp) {
        return ResponseEntity.ok(emailService.validateOtpInChangePassword(email, otp));
    }

}
