package com.digitaldark.ChambeaPe_Api.contract.service;

import com.digitaldark.ChambeaPe_Api.contract.dto.request.ContractRequestDTO;
import com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractPoResponseDTO;
import com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractResponseDTO;

import java.util.List;
public interface ContractService {

    public abstract ContractResponseDTO createContract(ContractRequestDTO contract);
    public abstract void deleteContract(int id);
    public abstract ContractResponseDTO updateContract(int id, ContractRequestDTO contract);
    public abstract List<ContractResponseDTO>  getContractByWorkerIdAndEmployerId(int workerId, int employerId);

    public abstract List<ContractResponseDTO> getAllContractsByUserId(int userId);

    public abstract List<List<ContractPoResponseDTO>> getAllContractsWithStatesByUserId(int userId);

}