package com.digitaldark.ChambeaPe_Api.contract.controller;

import com.digitaldark.ChambeaPe_Api.contract.dto.request.ContractRequestDTO;
import com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractPoResponseDTO;
import com.digitaldark.ChambeaPe_Api.contract.dto.response.ContractResponseDTO;
import com.digitaldark.ChambeaPe_Api.contract.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ContractController", description = "Controller to handle Contract")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ContractController {
    @Autowired
    private ContractService contractService;

    //URL: http://localhost:8080/api/v1/contracts
    //Method: POST
    @Operation(summary = "Create a contract")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, creating a contract",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContractResponseDTO.class)))
    @PostMapping("/contracts")
    public ResponseEntity<ContractResponseDTO> createContract(@Valid @RequestBody ContractRequestDTO contract) {
        return new ResponseEntity<ContractResponseDTO>(contractService.createContract(contract), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/v1/contracts/{id}
    //Method: DELETE
    @Operation(summary = "Delete a contract")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, deleting a contract",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContractResponseDTO.class)))
    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable("id") int id) {
        contractService.deleteContract(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/contracts/{id}
    //Method: PUT
    @Operation(summary = "Update a contract")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, updating a contract",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContractResponseDTO.class)))
    @PutMapping("/contracts/{id}")
    public ResponseEntity<ContractResponseDTO> updateContract(@PathVariable("id") int id, @Valid @RequestBody ContractRequestDTO contract) {
        return new ResponseEntity<ContractResponseDTO>(contractService.updateContract(id, contract), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/contracts
    //Method: GET
    @Operation(summary = "Get contract by workerId and employerId")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning contract by workerId and employerId",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContractResponseDTO.class)))
    @GetMapping("/contracts")
    public ResponseEntity<List<ContractResponseDTO>> getContractByWorkerIdAndEmployerId(@RequestParam("workerId") int workerId, @RequestParam("employerId") int employerId) {
        return new ResponseEntity<List<ContractResponseDTO>>(contractService.getContractByWorkerIdAndEmployerId(workerId, employerId), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/contracts/user/{userId}
    //Method: GET
    @Operation(summary = "Get all contracts by userdId")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all contracts by userId",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContractResponseDTO.class)))
    @GetMapping("/contracts/user/{userId}")
    public ResponseEntity<List<ContractResponseDTO>> getAllContractsByUserId(@PathVariable("userId") int userId) {
        return new ResponseEntity<List<ContractResponseDTO>>(contractService.getAllContractsByUserId(userId), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/contracts/user/{userId}/states
    //Method: GET
    @Operation(summary = "Get all contracts separated by states by userId")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all contracts with states by userId",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContractPoResponseDTO.class)))
    @GetMapping("/contracts/user/{userId}/states")
    public ResponseEntity<List<List<ContractPoResponseDTO>>> getAllContractsWithStatesByUserId(@PathVariable("userId") int userId) {
        return new ResponseEntity<List<List<ContractPoResponseDTO>>>(contractService.getAllContractsWithStatesByUserId(userId), HttpStatus.OK);
    }
}