package com.digitaldark.ChambeaPe_Api.user.service;

import com.digitaldark.ChambeaPe_Api.user.dto.WorkerDTO;
import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface WorkerService {
    public abstract WorkerEntity createWorker(WorkerEntity worker);
    public abstract List<WorkerEntity> getAllWorkers_v1();
    public abstract List<WorkerDTO> getAllWorkers();
    public abstract WorkerDTO getWorkerById(int id);
    public abstract void deleteWorker(int id);
    public abstract void updateWorker(int id, WorkerDTO worker) throws MessagingException, IOException;
}
