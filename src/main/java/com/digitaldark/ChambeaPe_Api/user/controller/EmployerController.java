package com.digitaldark.ChambeaPe_Api.user.controller;

import com.digitaldark.ChambeaPe_Api.user.dto.EmployerDTO;
import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import com.digitaldark.ChambeaPe_Api.user.service.EmployerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "EmployerController", description = "Controller to handle Employers")
@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:4200") // Puerto de Angular
@CrossOrigin(origins = "*")
public class EmployerController {
   @Autowired
    private EmployerService employerService;

    //URL: http://localhost:8080/api/v1/employersV1
    //Method: GET
    @Operation(summary = "Get all employers")
    @ApiResponse(responseCode = "200",
            description = "Successful operation, returning all employers",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployerEntity.class)))
    @Transactional(readOnly = true)
    @GetMapping("/employersV1")
    public ResponseEntity<List<EmployerEntity>> getAllEmployers() {
        return new ResponseEntity<List<EmployerEntity>>(employerService.getAllEmployers_v1(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/employers
    //Method: GET
    @Operation(summary = "Get all employers DTO")
    @ApiResponse(responseCode = "200",
            description = "Successful operation, returning all employers DTO",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployerDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/employers")
    public ResponseEntity<List<EmployerDTO>> getAllEmployersDTO() {
        return new ResponseEntity<List<EmployerDTO>>(employerService.getAllEmployers(), HttpStatus.OK);
    }
}
