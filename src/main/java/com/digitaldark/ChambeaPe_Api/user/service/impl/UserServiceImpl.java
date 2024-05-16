package com.digitaldark.ChambeaPe_Api.user.service.impl;

import com.digitaldark.ChambeaPe_Api.email.service.IEmailService;
import com.digitaldark.ChambeaPe_Api.shared.exception.ResourceNotFoundException;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.dto.request.UserLoginDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.request.UserRequestDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.response.UserResponseDTO;
import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import com.digitaldark.ChambeaPe_Api.user.model.UsersEntity;
import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import com.digitaldark.ChambeaPe_Api.user.repository.UserRepository;
import com.digitaldark.ChambeaPe_Api.user.service.EmployerService;
import com.digitaldark.ChambeaPe_Api.user.service.UserService;
import com.digitaldark.ChambeaPe_Api.user.service.WorkerService;
import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private IEmailService emailService;

    @Override
    public UsersEntity createUser(UsersEntity user) throws MessagingException, IOException {
        if (userRepository.existsById(user.getId())) {
            throw new ValidationException("User already exists");
        } else if (userRepository.existsByEmailOrPhoneNumber(user.getEmail(), user.getPhoneNumber())) {
            throw new ValidationException("Email or phone number already exists");
        } else if (!"M".equals(user.getGender()) && !"F".equals(user.getGender())) {
            throw new ValidationException("Gender is invalid");
        }

        userRepository.save(user);

        if ("E".equals(user.getUserRole())) {
            EmployerEntity employer = modelMapper.map(user, EmployerEntity.class);
            employer.setUser(user);
            System.out.println("El valor de user es: " + employer);

            employerService.createEmployer(employer);
        } else if ("W".equals(user.getUserRole())) {
            WorkerEntity worker = modelMapper.map(user, WorkerEntity.class);
            worker.setOccupation("");
            worker.setUser(user);
            System.out.println("El valor de user es: " + worker);
            workerService.createWorker(worker);
        } else {
            throw new ValidationException("userRole is invalid");
        }

        emailService.userRegistered(user.getEmail());
        return user;
    }

    @Override
    public UserResponseDTO createUserDTO(UserRequestDTO user) throws MessagingException, IOException {
        if (userRepository.existsByEmailOrPhoneNumber(user.getEmail(), user.getPhoneNumber())) {
            throw new ValidationException("Email or phone number already exists");
        }


        // Obtiene la hora actual en milisegundos
        long currentTimeMillis = System.currentTimeMillis();

        // Crea un objeto Timestamp con la hora actual
        Timestamp timestamp = new Timestamp(currentTimeMillis);

        System.out.println("El valor de user es: " + user);

        UsersEntity userEntity = modelMapper.map(user, UsersEntity.class);
        userEntity.setHasPremium( (byte) 0);
        userEntity.setIsActive( (byte) 1);
        userEntity.setDateCreated(timestamp);
        userEntity.setDateUpdated(timestamp);
        userEntity.setBirthdate(new Timestamp(0));
        userEntity.setOtp("");
        userEntity.setIsVisible("1");

        System.out.println("El valor de user es: " + userEntity);
        userRepository.save(userEntity);

        if ("E".equals(userEntity.getUserRole())) {
            EmployerEntity employer = modelMapper.map(userEntity, EmployerEntity.class);
            employer.setUser(userEntity);
            System.out.println("El valor de user es: " + employer);
            employerService.createEmployer(employer);
        } else if ("W".equals(userEntity.getUserRole())) {
            WorkerEntity worker = modelMapper.map(userEntity, WorkerEntity.class);
            worker.setOccupation("");
            worker.setUser(userEntity);
            System.out.println("El valor de user es: " + worker);
            workerService.createWorker(worker);
        } else {
            throw new ValidationException("userRole is invalid");
        }

        emailService.userRegistered(user.getEmail());

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User does not exist");
        }
        UsersEntity user = userRepository.findById(id);

        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UsersEntity> users = userRepository.findAll();

        List<UserResponseDTO> usersDTO = users
            .stream()
            .map(user -> modelMapper.map(user, UserResponseDTO.class))
            .collect(Collectors.toList());

        return usersDTO;
    }

    @Override
    public UserResponseDTO getUserEmailAndPass(UserLoginDTO userLoginDTO) {
        if (!userRepository.existsByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword())) {
            throw new ResourceNotFoundException("Email or password is incorrect");
        }
        UsersEntity user = userRepository.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());

        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(int id, UsersEntity user) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User does not exist");
        }

        userRepository.save(user);
    }


}