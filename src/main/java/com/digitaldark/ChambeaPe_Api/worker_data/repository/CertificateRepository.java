package com.digitaldark.ChambeaPe_Api.worker_data.repository;

import com.digitaldark.ChambeaPe_Api.worker_data.model.CertificatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CertificateRepository extends JpaRepository<CertificatesEntity, Integer> {

    CertificatesEntity findByUserName(String name );
    boolean existsByWorkerId(int id);

    List<CertificatesEntity> findAllByWorkerId(int id);

}
