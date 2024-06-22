package com.digitaldark.ChambeaPe_Api.user.repository;

import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<WorkerEntity, Integer> {

    WorkerEntity findById(int id);
    List<WorkerEntity> findAll();
}
