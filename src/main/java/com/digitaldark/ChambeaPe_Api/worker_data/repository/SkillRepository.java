package com.digitaldark.ChambeaPe_Api.worker_data.repository;

import com.digitaldark.ChambeaPe_Api.worker_data.model.SkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillsEntity, Integer> {

    List<SkillsEntity> findAllByWorkerId(int id);

}
