package com.digitaldark.ChambeaPe_Api.worker_data.repository;

import com.digitaldark.ChambeaPe_Api.worker_data.model.AdvertisementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementsEntity, Integer> {
    boolean existsByWorkerId(int id);
    AdvertisementsEntity findByWorkerId(int id);
    List<AdvertisementsEntity> findAllByWorkerId(int id);
}
