package com.digitaldark.ChambeaPe_Api.user.service.impl;

import com.digitaldark.ChambeaPe_Api.email.service.IEmailService;
import com.digitaldark.ChambeaPe_Api.shared.exception.ResourceNotFoundException;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.dto.WorkerDTO;
import com.digitaldark.ChambeaPe_Api.user.model.UsersEntity;
import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import com.digitaldark.ChambeaPe_Api.user.repository.UserRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.WorkerRepository;
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
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public WorkerEntity createWorker(WorkerEntity worker) {
        return workerRepository.save(worker);
    }

    @Override
    public List<WorkerEntity> getAllWorkers_v1() {
        return workerRepository.findAll();
    }

    @Autowired
    private IEmailService emailService;

    @Override
    public List<WorkerDTO> getAllWorkers() {
        List<WorkerEntity> workerEntities = workerRepository.findAll();

        // Convierte las entidades en una lista de DTO en un solo paso
        List<WorkerDTO> workerDTOs = workerEntities
                .stream()
                .map(workerEntity -> {
                    WorkerDTO workerDTO = modelMapper.map(workerEntity, WorkerDTO.class);
                    modelMapper.map(workerEntity.getUser(), workerDTO);
                    return workerDTO;
                })
                .collect(Collectors.toList());
        return workerDTOs;
    }

    @Override
    public WorkerDTO getWorkerById(int id) {
        if (!workerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Worker not found");
        }

        WorkerEntity workerEntity = workerRepository.findById(id);

        WorkerDTO workerDTO = modelMapper.map(workerEntity, WorkerDTO.class);
        modelMapper.map(workerEntity.getUser(), workerDTO);

        return workerDTO;
    }

    @Override
    public void deleteWorker(int id) {
        if (!workerRepository.existsById(id)) {
            throw new ValidationException("Worker does not exist");
        }
        workerRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    @Override
    public void updateWorker(int id, WorkerDTO worker) throws MessagingException, IOException {
        if (!workerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Worker does not exist");
        }
        validarWorkerDTO(worker);

        WorkerEntity workerEntity = modelMapper.map(workerRepository.findById(id), WorkerEntity.class);
        modelMapper.map(worker, workerEntity);
        workerEntity.setId(id);
        
        long currentTimeMillis = System.currentTimeMillis();

        Timestamp timestamp = new Timestamp(currentTimeMillis);
        workerEntity.setDateUpdated(timestamp);

        UsersEntity user = modelMapper.map(userRepository.findById(id), UsersEntity.class);
        modelMapper.map(worker, user);
        user.setId(id);
        user.setDateUpdated(timestamp);

        workerRepository.save(workerEntity);
        userRepository.save(user);
        emailService.userModified(user.getEmail());
    }

    void validarWorkerDTO(WorkerDTO workerDTO){
        if(workerDTO.getFirstName() == null
                || workerDTO.getLastName() == null || workerDTO.getEmail() == null
                || workerDTO.getPhoneNumber() == null || workerDTO.getProfilePic() == null
                || workerDTO.getDescription() == null){
            throw new ValidationException("Debe completar los campos requeridos");
        }
    }
}
