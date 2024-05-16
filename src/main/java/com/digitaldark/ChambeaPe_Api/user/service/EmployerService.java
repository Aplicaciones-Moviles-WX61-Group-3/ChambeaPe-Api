package com.digitaldark.ChambeaPe_Api.user.service;

import com.digitaldark.ChambeaPe_Api.user.dto.EmployerDTO;
import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface EmployerService {
    public abstract EmployerEntity createEmployer(EmployerEntity employer);
    public abstract List<EmployerEntity> getAllEmployers_v1();
    public abstract List<EmployerDTO> getAllEmployers();
    public abstract EmployerDTO getEmployerById(int id);
    public abstract void deleteEmployer(int id);
    public abstract void updateEmployer(int id, EmployerDTO employer) throws MessagingException, IOException;
}
