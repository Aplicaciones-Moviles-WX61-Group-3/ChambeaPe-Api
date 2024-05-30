package com.digitaldark.ChambeaPe_Api.contract.service.impl;

import com.digitaldark.ChambeaPe_Api.contract.dto.request.ContractRequestDTO;
import com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractResponseDTO;
import com.digitaldark.ChambeaPe_Api.contract.model.ContractEntity;
import com.digitaldark.ChambeaPe_Api.contract.repository.ContractRepository;
import com.digitaldark.ChambeaPe_Api.contract.service.ContractService;
import com.digitaldark.ChambeaPe_Api.post.repository.PostRepository;
import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ResourceNotFoundException;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.repository.EmployerRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl  implements ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private EmployerRepository employerRepository;
    
    @Autowired
    private DateTimeEntity dateTimeEntity;

    @Override
    public ContractResponseDTO createContract(ContractRequestDTO contract) {
        validateContractRequest(contract);


        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ContractEntity contractEntity = modelMapper.map(contract, ContractEntity.class);
        contractEntity.setState("disabled");
        contractEntity.setDateCreated(dateTimeEntity.currentTime());
        contractEntity.setDateUpdated(dateTimeEntity.currentTime());
        contractEntity.setIsActive((byte) 1);
        contractEntity.setPost(postRepository.findById(contract.getPostId()));

        contractRepository.save(contractEntity);
        return modelMapper.map(contractEntity, ContractResponseDTO.class);
    }

    @Override
    public void deleteContract(int id) {
        if (!contractRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contract not found");
        }
        contractRepository.deleteById(id);
    }

    @Override
    public ContractResponseDTO updateContract(int id, ContractRequestDTO contract) {
        if (!contractRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contract not found");
        }
        validateContractRequest(contract);

        ContractEntity contractEntity = contractRepository.findById(id);
        modelMapper.map(contract, contractEntity);
        contractEntity.setDateUpdated(dateTimeEntity.currentTime());
        contractEntity.setId(id);

        contractRepository.save(contractEntity);

        return modelMapper.map(contractEntity, ContractResponseDTO.class);
    }

    @Override
    public ContractResponseDTO getContractByWorkerIdAndEmployerId(int workerId, int employerId) {
        if (!workerRepository.existsById(workerId)) {
            throw new ResourceNotFoundException("Worker not found");
        }
        if (!employerRepository.existsById(employerId)) {
            throw new ResourceNotFoundException("Employer not found");
        }

        ContractEntity contractEntity = contractRepository.findByWorkerIdAndEmployerId(workerId, employerId);
        return modelMapper.map(contractEntity, ContractResponseDTO.class);
    }

    @Override
    public List<ContractResponseDTO> getAllContractsByUserId(int userId) {
        if (!workerRepository.existsById(userId) && !employerRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        return contractRepository.findByAllByUserId(userId);
    }
    
    public void validateContractRequest(ContractRequestDTO contract) {
        if (!workerRepository.existsById(contract.getWorkerId())) {
            throw new ResourceNotFoundException("Worker not found");
        }
        if (!employerRepository.existsById(contract.getEmployerId())) {
            throw new ResourceNotFoundException("Employer not found");
        }
        if (!postRepository.existsById(contract.getPostId())) {
            throw new ResourceNotFoundException("Post not found");
        }
        if(contract.getSalary() < 0) {
            throw new ValidationException("Salary must be greater than 0");
        }
        if(contract.getStartDay().after(contract.getEndDay())) {
            throw new ValidationException("Start day must be before end day");
        }
    }
}
