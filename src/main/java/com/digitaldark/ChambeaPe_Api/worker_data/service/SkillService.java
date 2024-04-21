package com.digitaldark.ChambeaPe_Api.worker_data.service;

import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.SkillRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.SkillResponseDTO;

import java.util.List;

public interface SkillService {
    public abstract SkillResponseDTO createSkill(SkillRequestDTO skill, int id);
    public abstract List<SkillResponseDTO> getAllSkillsByWorkerId(int id);
    public abstract void deleteSkillById(int id);

}
