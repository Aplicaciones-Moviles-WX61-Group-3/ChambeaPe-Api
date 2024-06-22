package com.digitaldark.ChambeaPe_Api.worker_data.service;

import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.CertificateRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.CertificateResponseDTO;

import java.util.List;
public interface CertificateService {

    public abstract CertificateResponseDTO createCertificate(CertificateRequestDTO certificate, int id);
    public abstract CertificateResponseDTO getCertificateById(int id);
    public abstract List<CertificateResponseDTO> getAllCertificatesByWorkerId(int id);
    public abstract CertificateResponseDTO updateCertificate(int id, CertificateRequestDTO certificate);
    public abstract void deleteCertificateById(int id);
}
