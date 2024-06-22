package com.digitaldark.ChambeaPe_Api.worker_data.controller;

import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.AdvertisementRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.AdvertisementResponseDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.service.AdvertisementService;
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

@Tag(name = "Advertisement", description = "Controller to handle Advertisement")
@RestController
@RequestMapping("/api/v1/workers")
@CrossOrigin(origins = "*") // Puerto de Angular

public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    //URL: http://localhost:8080/api/v1/worker/{id}/advertisements
    //Method: GET ALL
    @Operation(summary = "Get all Advertisements by Worker Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all Advertisements by Worker Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AdvertisementResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/{id}/advertisements")
    public ResponseEntity<List<AdvertisementResponseDTO>> getAllAdvertisementsByWorkerId(@PathVariable(value = "id") int userId) {
        return new ResponseEntity<List<AdvertisementResponseDTO>>(advertisementService.getAllAdvertisementsByWorkerId(userId), HttpStatus.OK);
    }

    //method: Get
    //URL: http://localhost:8080/api/v1/worker/{id}/advertisements/{id}
    @Operation(summary = "Get Advertisement by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Advertisement by Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AdvertisementResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/{id}/advertisements/{advertisementId}")
    public ResponseEntity<AdvertisementResponseDTO> getAdvertisementById(
            @PathVariable(value = "advertisementId") int advertisementId) {
        return new ResponseEntity<AdvertisementResponseDTO>(advertisementService.getAdvertisementById(advertisementId), HttpStatus.OK);
    }

    //method: Post
    //URL: http://localhost:8080/api/v1/worker/{id}/advertisements
    @Operation(summary = "Create Advertisement")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Advertisement created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AdvertisementResponseDTO.class)))
    @Transactional
    @PostMapping("/{id}/advertisements")
    public ResponseEntity<AdvertisementResponseDTO> createAdvertisement(@Valid @RequestBody AdvertisementRequestDTO advertisement, @PathVariable(value = "id") int userId) {
        return new ResponseEntity<AdvertisementResponseDTO>(advertisementService.createAdvertisement(advertisement, userId), HttpStatus.CREATED);
    }


    //method: Put
    //URL: http://localhost:8080/api/v1/worker/{id}/advertisements/{id}
    @Operation(summary = "Update Advertisement by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Advertisement updated",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AdvertisementResponseDTO.class)))
    @Transactional
    @PutMapping("/{id}/advertisements/{advertisementId}")
    public ResponseEntity<AdvertisementResponseDTO> updateAdvertisement(
            @PathVariable(value = "id") int userId,
            @PathVariable(value = "advertisementId") int advertisementId,
            @Valid @RequestBody AdvertisementRequestDTO advertisementDetails) {
        return new ResponseEntity<AdvertisementResponseDTO>(advertisementService.updateAdvertisement(advertisementId, advertisementDetails), HttpStatus.OK);
    }

    //method: Delete
    //URL: http://localhost:8080/api/v1/worker/{id}/advertisements/{id}
    @Operation(summary = "Delete Advertisement by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, Advertisement deleted",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AdvertisementResponseDTO.class)))
    @Transactional
    @DeleteMapping("/{id}/advertisements/{advertisementId}")
    public ResponseEntity<Void> deleteAdvertisement(
            @PathVariable(value = "id") int userId,
            @PathVariable(value = "advertisementId") int advertisementId) {
        advertisementService.deleteAdvertisementById(advertisementId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
