package com.digitaldark.ChambeaPe_Api.worker_data.service;

import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.AdvertisementRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.AdvertisementResponseDTO;

import java.util.List;

public interface AdvertisementService {
    public abstract AdvertisementResponseDTO createAdvertisement(AdvertisementRequestDTO advertisement, int id);
    public abstract AdvertisementResponseDTO getAdvertisementById(int id);
    public abstract AdvertisementResponseDTO updateAdvertisement(int id, AdvertisementRequestDTO advertisement);
    public abstract List<AdvertisementResponseDTO> getAllAdvertisementsByWorkerId(int id);
    public abstract void deleteAdvertisementById(int id);

}
