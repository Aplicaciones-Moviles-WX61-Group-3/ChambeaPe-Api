package com.digitaldark.ChambeaPe_Api.worker_data.repository;

import com.digitaldark.ChambeaPe_Api.worker_data.model.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Integer> {
    boolean existsByWorkerId(int id);
    List<PortfolioEntity> findAllByWorkerId(int id);

}
