package com.digitaldark.ChambeaPe_Api.chat.repository;

import com.digitaldark.ChambeaPe_Api.chat.model.ChatEntity;
import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
        ChatEntity findById(int id);
        boolean existsById(int id);

        boolean existsByIdAndEmployer(int id, EmployerEntity employer);
        boolean existsByIdAndWorker(int id, WorkerEntity worker);
        boolean  existsByEmployer(EmployerEntity employer);
        boolean existsByWorker(WorkerEntity worker);
        List<ChatEntity> findByEmployer(EmployerEntity employer);
        List<ChatEntity> findByWorker(WorkerEntity worker);
}
