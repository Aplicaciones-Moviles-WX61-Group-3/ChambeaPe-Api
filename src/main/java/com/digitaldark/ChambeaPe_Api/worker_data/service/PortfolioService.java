package com.digitaldark.ChambeaPe_Api.worker_data.service;

import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.PortfolioRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.PortfolioResponseDTO;

import java.util.List;

public interface PortfolioService {
    public abstract PortfolioResponseDTO createPortfolio(PortfolioRequestDTO portfolio, int id);
    public abstract PortfolioResponseDTO getPortfolioById(int id);
    public abstract List<PortfolioResponseDTO> getAllPortfoliosByWorkerId(int id);
    public abstract void deletePortfolioById(int id);

}