package com.digitaldark.ChambeaPe_Api.user.controller;

import com.digitaldark.ChambeaPe_Api.user.dto.WorkerDTO;
import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import com.digitaldark.ChambeaPe_Api.user.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "WorkerController", description = "Controller for Worker")
@RestController
//@CrossOrigin(origins = "http://localhost:4200") // Puerto de Angular
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    //URL: http://localhost:8080/api/v1/workersV1
    //Method: GET
    @Operation(summary = "Get all Workers")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all Workers",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkerEntity.class)))

    @Transactional(readOnly = true)
    @GetMapping("/workersV1")
    public ResponseEntity<List<WorkerEntity>> getAllWorkers() {
        return new ResponseEntity<List<WorkerEntity>>(workerService.getAllWorkers_v1(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/workers
    //Method: GET
    @Operation(summary = "Get all Workers")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all Workers",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkerDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/workers")
    public ResponseEntity<List<WorkerDTO>> getAllWorkersDTO() {
        return new ResponseEntity<List<WorkerDTO>>(workerService.getAllWorkers(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/workers/{id}
    //Method: GET
    @Operation(summary = "Get Worker by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Worker by Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkerDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/workers/{id}")
    public ResponseEntity<WorkerDTO> getWorkerById(@PathVariable("id") int id) {
        return new ResponseEntity<WorkerDTO>(workerService.getWorkerById(id), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/workers/{id}
    //Method: DELETE
    @Operation(summary = "Delete Worker by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Worker deleted",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkerDTO.class)))
    @Transactional
    @DeleteMapping("/workers/{id}")
    public ResponseEntity<Object> deleteWorker(@PathVariable("id") int id) {
        workerService.deleteWorker(id);
        return new ResponseEntity<>("Worker was deleted successfully",HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/workers/{id}
    //Method: PUT
    @Operation(summary = "Update Worker by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Worker updated",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WorkerDTO.class)))
    @Transactional
    @PutMapping("/workers/{id}")
    public ResponseEntity<Object> updateWorker(@PathVariable("id") int id, @Valid @RequestBody WorkerDTO worker) throws MessagingException, IOException {
        workerService.updateWorker(id, worker);
        return new ResponseEntity<>("Worker was updated successfully",HttpStatus.OK);
    }

}
