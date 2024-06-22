package com.digitaldark.ChambeaPe_Api.worker_data.controller;


import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.SkillRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.SkillResponseDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SkillController", description = "Controller for Skill")
@RestController
@RequestMapping("/api/v1/workers")
@CrossOrigin(origins = "*")
public class SkillController {
    @Autowired
    private SkillService skillService;

    //URL: http://localhost:8080/api/v1/workers/{id}/skills
    //Method: GET ALL
    @Operation(summary = "Get all Skills by Worker Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all Skills by Worker Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SkillResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/{id}/skills")
    public ResponseEntity<List<SkillResponseDTO>> getAllSkillsByWorkerId(@PathVariable(value = "id") int userId) {
        return new ResponseEntity<List<SkillResponseDTO>>(skillService.getAllSkillsByWorkerId(userId), HttpStatus.OK);
    }

    //method: Post
    //URL: http://localhost:8080/api/v1/workers/{id}/skills
    @Operation(summary = "Create Skill")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Skill created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SkillResponseDTO.class)))
    @Transactional
    @PostMapping("/{id}/skills")
    public ResponseEntity<SkillResponseDTO> createSkill(@Valid @RequestBody SkillRequestDTO skill, @PathVariable(value = "id") int userId) {
        return new ResponseEntity<SkillResponseDTO>(skillService.createSkill(skill, userId), HttpStatus.CREATED);
    }

    //method: Delete
    //URL: http://localhost:8080/api/v1/workers/{id}/skills/{id}
    @Operation(summary = "Delete Skill by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Skill deleted",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SkillResponseDTO.class)))
    @Transactional
    @DeleteMapping("/{userId}/skills/{skillId}")
    public ResponseEntity<SkillResponseDTO> deleteSkill(
            @PathVariable(value = "userId") int userId,
            @PathVariable(value = "skillId") int skillId) {

        skillService.deleteSkillById(skillId);
        return new ResponseEntity<SkillResponseDTO>(HttpStatus.OK);
    }



}
