package com.digitaldark.ChambeaPe_Api.email.service.impl;

import com.digitaldark.ChambeaPe_Api.email.service.IEmailService;
import com.digitaldark.ChambeaPe_Api.email.util.OtpUtil;
import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.dto.response.UserResponseDTO;
import com.digitaldark.ChambeaPe_Api.user.model.UsersEntity;
import com.digitaldark.ChambeaPe_Api.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EmailServiceImpl implements IEmailService {

    @Value("${email.account}")
    private String emailAccount;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DateTimeEntity dateTimeEntity;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void sendEmail(String[] toUser, String subject, String message) throws MessagingException, IOException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailAccount);
        mailMessage.setTo(toUser);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);

        userRegistered(toUser[0]);
    }

    @Override
    public void userRegistered(String toUser) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setFrom(emailAccount);
        message.setTo(toUser);
        message.setSubject("Bienvenido a ChambeaPe");

        // Cuerpo del correo electrónico en HTML
        String htmlContent = "<h1>Bienvenido a ChambeaPe</h1>" +
                "<p>¡Gracias por registrarte en nuestra plataforma!</p>" +
                "<img src=\"https://img.freepik.com/foto-gratis/vista-lateral-pareja-sonriente-interior_23-2149903726.jpg\" alt=\"Logo\">";

        message.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }

    @Override
    public void resetPassword(String toUser) throws MessagingException, IOException {

        if (!userRepository.existsByEmail(toUser)) {
            throw new ValidationException("User email does not exist");
        }

        String otp = otpUtil.generateOtp();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setFrom(emailAccount);
        message.setTo(toUser);
        message.setSubject("Restablecer contraseña");

        // Cuerpo del correo electrónico en HTML
        String htmlContent = "<h1>Restablecer contraseña</h1>" +
                "<p>Para restablecer tu contraseña, ingresa el siguiente código:</p>" +
                "<h2>" + otp + "</h2>";

        message.setText(htmlContent, true);

        mailSender.send(mimeMessage);

        // Guardar OTP en la base de datos

        UsersEntity user = userRepository.findByEmail(toUser);
        user.setOtp(otp);
        user.setOtpGeneratedTime(dateTimeEntity.currentTime().toLocalDateTime());
        userRepository.save(user);
    }

    @Override
    public UserResponseDTO validateOtpInChangePassword(String email, String otp) {
        UsersEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ValidationException("User does not exist");
        }

        if (!otp.equals(user.getOtp())) {
            throw new ValidationException("Invalid OTP");
        }

        if (dateTimeEntity.currentTime().toLocalDateTime().isAfter(user.getOtpGeneratedTime().plusMinutes(4))) {
            throw new ValidationException("OTP has expired");
        }

        return modelMapper.map(user, UserResponseDTO.class);
    }

}
