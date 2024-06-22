package com.digitaldark.ChambeaPe_Api.worker_data.service.impl;

import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.repository.UserRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.WorkerRepository;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.PortfolioRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.PortfolioResponseDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.model.PortfolioEntity;
import com.digitaldark.ChambeaPe_Api.worker_data.repository.PortfolioRepository;
import com.digitaldark.ChambeaPe_Api.worker_data.service.PortfolioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DateTimeEntity dateTimeEntity;
    @Override
    public PortfolioResponseDTO createPortfolio(PortfolioRequestDTO portfolio, int id) {

        PortfolioEntity portfolioEntity = modelMapper.map(portfolio, PortfolioEntity.class);

        portfolioEntity.setDateCreated(dateTimeEntity.currentTime());
        portfolioEntity.setDateUpdated(dateTimeEntity.currentTime());
        portfolioEntity.setWorker(workerRepository.findById(id));
        portfolioEntity.setIsActive((byte) 1);

        portfolioRepository.save(portfolioEntity);

        return modelMapper.map(portfolioEntity, PortfolioResponseDTO.class);
    }

    @Override
    public PortfolioResponseDTO getPortfolioById(int id) {
        if (!portfolioRepository.existsById(id)){
            throw new ValidationException("Portfolio does not exist");
        }
        Optional<PortfolioEntity> portfolioEntity = portfolioRepository.findById(id);

        return modelMapper.map(portfolioEntity, PortfolioResponseDTO.class);
    }

    @Override
    public List<PortfolioResponseDTO> getAllPortfoliosByWorkerId(int id) {
        if( !workerRepository.existsById(id)){
            throw new ValidationException("Worker does not exist");
        }
        List<PortfolioEntity> portfolioEntities = portfolioRepository.findAllByWorkerId(id);
        return portfolioEntities.stream().map(portfolioEntity -> modelMapper.map(portfolioEntity, PortfolioResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deletePortfolioById(int id) {
        if (!portfolioRepository.existsById(id)){
            throw new ValidationException("Portfolio does not exist");
        }
        portfolioRepository.deleteById(id);

    }
}
