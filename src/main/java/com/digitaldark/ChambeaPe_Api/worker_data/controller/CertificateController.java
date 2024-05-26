package com.digitaldark.ChambeaPe_Api.worker_data.controller;


import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.CertificateRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.CertificateResponseDTO;

import com.digitaldark.ChambeaPe_Api.worker_data.service.CertificateService;

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

@Tag(name = "CertificateController", description = "Controller for Certificate")
@RestController
@RequestMapping("/api/v1/workers")
@CrossOrigin(origins = "*") // Puerto de Angular
public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    //URL: http://localhost:8080/api/v1/users/{id}/certificates
    //Method: GET ALL
    @Operation(summary = "Get all Certificates by Worker Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all Certificates by Worker Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CertificateResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/{id}/certificates")
    public ResponseEntity<List<CertificateResponseDTO>> getAllCertificatesByWorkerId(@PathVariable(value = "id") int userId) {
        return new ResponseEntity<List<CertificateResponseDTO>>(certificateService.getAllCertificatesByWorkerId(userId), HttpStatus.OK);
    }

    //Method : Post
    //URL: http://localhost:8080/api/v1/users/{id}/certificates
    @Operation(summary = "Create Certificate")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Certificate created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CertificateResponseDTO.class)))
    @Transactional
    @PostMapping("/{id}/certificates")
    public ResponseEntity<CertificateResponseDTO> createCertificate(@Valid @RequestBody CertificateRequestDTO certificate, @PathVariable(value = "id") int userId) {
        return new ResponseEntity<CertificateResponseDTO>(certificateService.createCertificate(certificate, userId), HttpStatus.CREATED);
    }

    //method: Get
    //URL: http://localhost:8080/api/v1/users/{id}/certificates/{id}
    @Operation(summary = "Get Certificate by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Certificate by Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CertificateResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/{userId}/certificates/{certificateId}")
    public ResponseEntity<CertificateResponseDTO> getCertificateById(
            @PathVariable(value = "certificateId") int certificateId) {
        return new ResponseEntity<CertificateResponseDTO>(certificateService.getCertificateById(certificateId), HttpStatus.OK);
    }

    //method: Put
    //URL: http://localhost:8080/api/v1/users/{id}/certificates/{id}
    @Operation(summary = "Update Certificate by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Certificate updated",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CertificateResponseDTO.class)))
    @Transactional
    @PutMapping("/{userId}/certificates/{certificateId}")
    public ResponseEntity<CertificateResponseDTO> updateCertificate(
            @PathVariable(value = "userId") int userId,
            @PathVariable(value = "certificateId") int certificateId,
            @Valid @RequestBody CertificateRequestDTO certificateDetails) {
        return new ResponseEntity<CertificateResponseDTO>(certificateService.updateCertificate(certificateId, certificateDetails), HttpStatus.OK);
    }

    //method: Delete
    //URL: http://localhost:8080/api/v1/users/{id}/certificates/{id}
    @Operation(summary = "Delete Certificate by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Certificate deleted",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CertificateResponseDTO.class)))
    @Transactional
    @DeleteMapping("/{userId}/certificates/{certificateId}")
    public ResponseEntity<?> deleteCertificate(
            @PathVariable(value = "userId") int userId,
            @PathVariable(value = "certificateId") int certificateId) {
        certificateService.deleteCertificateById(certificateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
