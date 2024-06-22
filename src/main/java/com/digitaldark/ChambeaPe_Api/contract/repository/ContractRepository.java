package com.digitaldark.ChambeaPe_Api.contract.repository;

import com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractResponseDTO;
import com.digitaldark.ChambeaPe_Api.contract.model.ContractEntity;
import com.digitaldark.ChambeaPe_Api.post.model.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository  extends JpaRepository<ContractEntity, Integer> {
    ContractEntity findById(int id);
    boolean existsById(int id);

    boolean existsByPost(PostsEntity post);
    List<ContractEntity> findByWorkerIdAndEmployerId(int workerId, int employerId);

    @Query("SELECT new com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractResponseDTO(" +
            "c.id, c.workerId, c.employerId, c.startDay, c.endDay, c.salary,c.state , c.post.id ) " +
            "FROM ContractEntity c " +
            "WHERE c.workerId = :userId OR c.employerId = :userId")
    List<ContractResponseDTO> findByAllByUserId(int userId);
}