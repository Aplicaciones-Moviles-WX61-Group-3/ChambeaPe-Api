package com.digitaldark.ChambeaPe_Api.worker_data.service.impl;


import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.repository.UserRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.WorkerRepository;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.SkillRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.SkillResponseDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.model.SkillsEntity;
import com.digitaldark.ChambeaPe_Api.worker_data.repository.SkillRepository;
import com.digitaldark.ChambeaPe_Api.worker_data.service.SkillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private DateTimeEntity dateTimeEntity;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SkillResponseDTO createSkill(SkillRequestDTO skill, int id) {

        SkillsEntity skillEntity = modelMapper.map(skill, SkillsEntity.class);
        skillEntity.setDateCreated(dateTimeEntity.currentTime());
        skillEntity.setDateUpdated(dateTimeEntity.currentTime());
        skillEntity.setWorker(workerRepository.findById(id));
        skillEntity.setContent(skill.getContent());
        skillEntity.setIsActive((byte) 1);

        skillRepository.save(skillEntity);

        return modelMapper.map(skillEntity, SkillResponseDTO.class);
    }

    @Override
    public List<SkillResponseDTO> getAllSkillsByWorkerId(int id) {
        if(!workerRepository.existsById(id)){
            throw new ValidationException("Worker does not exist");
        }
        List<SkillsEntity> skills = skillRepository.findAllByWorkerId(id);
        return skills.stream().map(skill -> modelMapper.map(skill, SkillResponseDTO.class)).collect(Collectors.toList());

    }


    @Override
    public void deleteSkillById(int id) {
        if(!skillRepository.existsById(id)){
            throw new ValidationException("Skill does not exist");
        }
        skillRepository.deleteById(id);
    }
}
