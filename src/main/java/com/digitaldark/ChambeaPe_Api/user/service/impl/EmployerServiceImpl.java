package com.digitaldark.ChambeaPe_Api.user.service.impl;

import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.dto.EmployerDTO;
import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import com.digitaldark.ChambeaPe_Api.user.model.UsersEntity;
import com.digitaldark.ChambeaPe_Api.user.repository.EmployerRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.UserRepository;
import com.digitaldark.ChambeaPe_Api.user.service.EmployerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl implements EmployerService {
  
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmployerEntity createEmployer(EmployerEntity employer) {
        return employerRepository.save(employer);
    }

    @Override
    public List<EmployerEntity> getAllEmployers_v1() {
        return employerRepository.findAll();
    }

    @Override
    public List<EmployerDTO> getAllEmployers() {
        List<EmployerEntity> employerEntities = employerRepository.findAll();

        // EmployerDTO las entidades en una lista de DTO en un solo paso
        List<EmployerDTO> employerDTOs = employerEntities
                .stream()
                .map(employerEntity -> {
                    EmployerDTO employerDTO = modelMapper.map(employerEntity, EmployerDTO.class);
                    modelMapper.map(employerEntity.getUser(), employerDTO);
                    return employerDTO;
                })
                .collect(Collectors.toList());
        return employerDTOs;
    }

    @Override
    public EmployerDTO getEmployerById(int id) {
        if (!employerRepository.existsById(id)) {
            throw new ValidationException("Employer does not exist");
        }

        EmployerEntity employerEntity = employerRepository.findById(id);
        EmployerDTO employerDTO = modelMapper.map(employerEntity, EmployerDTO.class);
        modelMapper.map(employerEntity.getUser(), employerDTO);
        return employerDTO;
    }

    @Override
    public void deleteEmployer(int id) {
        if (!employerRepository.existsById(id)) {
            throw new ValidationException("Employer does not exist");
        }

        employerRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    void validarEmployerDTO(EmployerDTO employerDTO){
        if(employerDTO.getFirstName() == null
                || employerDTO.getLastName() == null || employerDTO.getEmail() == null
                || employerDTO.getPhoneNumber() == null || employerDTO.getProfilePic() == null
                || employerDTO.getDescription() == null){
            throw new ValidationException("Debe completar los campos requeridos");
        }
    }
}
