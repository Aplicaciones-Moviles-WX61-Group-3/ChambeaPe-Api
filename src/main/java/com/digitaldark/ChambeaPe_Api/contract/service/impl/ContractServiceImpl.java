package com.digitaldark.ChambeaPe_Api.contract.service.impl;

import com.digitaldark.ChambeaPe_Api.contract.dto.request.ContractRequestDTO;
import com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractPoResponseDTO;
import com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractResponseDTO;
import com.digitaldark.ChambeaPe_Api.contract.model.ContractEntity;
import com.digitaldark.ChambeaPe_Api.contract.repository.ContractRepository;
import com.digitaldark.ChambeaPe_Api.contract.service.ContractService;
import com.digitaldark.ChambeaPe_Api.post.dto.response.PostResponseDTO;
import com.digitaldark.ChambeaPe_Api.post.model.PostsEntity;
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
import java.util.Objects;
import java.util.stream.Collectors;

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

        if (contractRepository.existsByPost(postRepository.findById(contract.getPostId()))) {
            throw new ValidationException("Post already has a contract");
        }
        if (!Objects.equals(contract.getState(), "PENDING")) {
            throw new ValidationException("State must be PENDING");
        }

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ContractEntity contractEntity = modelMapper.map(contract, ContractEntity.class);
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

        if (!Objects.equals(contract.getState(), "PENDING") && !Objects.equals(contract.getState(), "ACCEPTED")) {
            throw new ValidationException("State must be PENDING or ACCEPTED");
        }

        validateContractRequest(contract);

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ContractEntity contractEntity = contractRepository.findById(id);
        modelMapper.map(contract, contractEntity);
        contractEntity.setDateUpdated(dateTimeEntity.currentTime());
        contractEntity.setId(id);

        contractRepository.save(contractEntity);

        return modelMapper.map(contractEntity, ContractResponseDTO.class);
    }

    @Override
    public List<ContractResponseDTO> getContractByWorkerIdAndEmployerId(int workerId, int employerId) {
        if (!workerRepository.existsById(workerId)) {
            throw new ResourceNotFoundException("Worker not found");
        }
        if (!employerRepository.existsById(employerId)) {
            throw new ResourceNotFoundException("Employer not found");
        }

        List<ContractEntity> contract = contractRepository.findByWorkerIdAndEmployerId(workerId, employerId);

        return contract.stream().map(contractEntity -> modelMapper.map(contractEntity, ContractResponseDTO.class)).toList();
    }

    @Override
    public List<ContractResponseDTO> getAllContractsByUserId(int userId) {
        if (!workerRepository.existsById(userId) && !employerRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        return contractRepository.findByAllByUserId(userId);
    }

    @Override
    public List<List<ContractPoResponseDTO>> getAllContractsWithStatesByUserId(int userId) {
        if (!workerRepository.existsById(userId) && !employerRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        List<ContractResponseDTO> contracts = contractRepository.findByAllByUserId(userId);

        List<ContractPoResponseDTO> contractsPo = contracts
                .stream()
                .map(contract -> {
                    ContractPoResponseDTO contractPo = modelMapper.map(contract, ContractPoResponseDTO.class);
                    PostsEntity post = postRepository.findById(contract.getPostId());
                    PostResponseDTO postResponseDTO = modelMapper.map(post, PostResponseDTO.class);
                    contractPo.setPost(postResponseDTO);
                    return contractPo;
                })
                .collect(Collectors.toList());

        List<ContractPoResponseDTO> pending = contractsPo.stream().filter(contract -> contract.getState().equals("PENDING")).toList();
        List<ContractPoResponseDTO> accepted = contractsPo.stream().filter(contract -> contract.getState().equals("ACCEPTED")).toList();

        return List.of(pending, accepted);
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
