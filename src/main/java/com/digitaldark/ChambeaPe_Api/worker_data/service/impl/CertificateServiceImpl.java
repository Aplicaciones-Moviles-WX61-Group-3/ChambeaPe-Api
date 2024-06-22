package com.digitaldark.ChambeaPe_Api.worker_data.service.impl;

import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.repository.UserRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.WorkerRepository;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.CertificateRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.CertificateResponseDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.model.CertificatesEntity;
import com.digitaldark.ChambeaPe_Api.worker_data.repository.CertificateRepository;
import com.digitaldark.ChambeaPe_Api.worker_data.service.CertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DateTimeEntity dateTimeEntity;

    @Override
    public CertificateResponseDTO createCertificate(CertificateRequestDTO certificate, int id) {
        //UsersEntity userEntity = modelMapper.map(user, UsersEntity.class);
        System.out.println("El valor de Certificate: " + certificate);
        CertificatesEntity certificateEntity = modelMapper.map(certificate, CertificatesEntity.class);
        System.out.println("El valor de CertificateEntity: " + certificateEntity);

        certificateEntity.setDateCreated(dateTimeEntity.currentTime());
        certificateEntity.setDateUpdated(dateTimeEntity.currentTime());
        certificateEntity.setWorker(workerRepository.findById(id));
        certificateEntity.setUserName(userRepository.findById(id).getFirstName()+" "+userRepository.findById(id).getLastName());
        certificateEntity.setIsActive((byte) 1);

        System.out.println("El valor de CertificateEntity es: " + certificateEntity);

        certificateRepository.save(certificateEntity);

        return modelMapper.map(certificateEntity,CertificateResponseDTO.class);
    }

    @Override
    public List<CertificateResponseDTO> getAllCertificatesByWorkerId(int id) {
        if(!certificateRepository.existsByWorkerId(id)){
            throw new ValidationException("Worker does not exist");
        }
        List<CertificatesEntity> certificateEntities = certificateRepository.findAllByWorkerId(id);
        return certificateEntities.stream()
                .map(certificateEntity -> modelMapper.map(certificateEntity, CertificateResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CertificateResponseDTO getCertificateById(int id) {
        Optional<CertificatesEntity> certificateEntityOptional = certificateRepository.findById(id);

        if (certificateEntityOptional.isEmpty()) {
            throw new ValidationException("Certificate does not exist");
        }

        CertificatesEntity certificateEntity = certificateEntityOptional.get();
        return modelMapper.map(certificateEntity, CertificateResponseDTO.class);
    }

    @Override
    public CertificateResponseDTO updateCertificate(int id, CertificateRequestDTO certificate) {
        if(!certificateRepository.existsById(id)){
            throw new ValidationException("Certificate does not exist");
        }

        CertificatesEntity certificateEntity = certificateRepository.findById(id).get();
        certificateEntity.setCertificateName(certificate.getCertificateName());
        certificateEntity.setInstitutionName(certificate.getInstitutionName());
        certificateEntity.setIssueDate(certificate.getIssueDate());
        certificateEntity.setImgUrl(certificate.getImgUrl());
        certificateEntity.setDateUpdated(dateTimeEntity.currentTime());
        certificateRepository.save(certificateEntity);
        return modelMapper.map(certificateEntity, CertificateResponseDTO.class);

    }
    @Override
    public void deleteCertificateById(int id) {
        certificateRepository.deleteById(id);
    }
}
