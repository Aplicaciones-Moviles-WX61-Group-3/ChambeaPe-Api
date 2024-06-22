package com.digitaldark.ChambeaPe_Api.worker_data.service.impl;

import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.repository.WorkerRepository;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.AdvertisementRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.AdvertisementResponseDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.model.AdvertisementsEntity;
import com.digitaldark.ChambeaPe_Api.worker_data.repository.AdvertisementRepository;
import com.digitaldark.ChambeaPe_Api.worker_data.service.AdvertisementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private DateTimeEntity dateTimeEntity;

    @Override
    public AdvertisementResponseDTO createAdvertisement(AdvertisementRequestDTO advertisement, int id) {
        AdvertisementsEntity advertisementEntity = modelMapper.map(advertisement, AdvertisementsEntity.class);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        advertisementEntity.setDateCreated(timestamp);
        advertisementEntity.setDateUpdated(timestamp);
        advertisementEntity.setIsActive((byte) 1);
        advertisementEntity.setWorker(workerRepository.findById(id));
        advertisementRepository.save(advertisementEntity);
        return modelMapper.map(advertisementEntity, AdvertisementResponseDTO.class);
    }

    @Override
    public AdvertisementResponseDTO getAdvertisementById(int id) {
        if( !advertisementRepository.existsByWorkerId(id) ){
            throw new ValidationException("Worker does not exist");
        }
        Optional<AdvertisementsEntity> advertisementEntity = advertisementRepository.findById(id);
        return modelMapper.map(advertisementEntity, AdvertisementResponseDTO.class);
    }

    @Override
    public AdvertisementResponseDTO updateAdvertisement(int id, AdvertisementRequestDTO advertisement) {
        if (!advertisementRepository.existsByWorkerId(id) ){
            throw new ValidationException("Worker does not exist");
        }

        AdvertisementsEntity advertisementEntity = advertisementRepository.findByWorkerId(id);
        advertisementEntity.setCategory(advertisement.getCategory());
        advertisementEntity.setText(advertisement.getText());
        advertisementEntity.setEndDay(advertisement.getEndDate());
        advertisementEntity.setPictureUrl(advertisement.getPictureUrl());
        advertisementEntity.setDateUpdated(dateTimeEntity.currentTime());

        advertisementRepository.save(advertisementEntity);
        return modelMapper.map(advertisementEntity, AdvertisementResponseDTO.class);
    }

    @Override
    public List<AdvertisementResponseDTO> getAllAdvertisementsByWorkerId(int id) {
        if( !advertisementRepository.existsByWorkerId(id) ){
            throw new ValidationException("Worker does not exist");
        }
        List<AdvertisementsEntity> advertisementsEntityList = advertisementRepository.findAllByWorkerId(id);
        return advertisementsEntityList.stream()
                .map( advertisementsEntity -> modelMapper.map(advertisementsEntity, AdvertisementResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdvertisementById(int id) {
        if( !advertisementRepository.existsByWorkerId(id) ){
            throw new ValidationException("Worker does not exist");
        }
        advertisementRepository.deleteById(id);
    }
}
