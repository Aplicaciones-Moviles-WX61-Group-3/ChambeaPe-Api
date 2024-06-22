package com.digitaldark.ChambeaPe_Api.worker_data.controller;

import com.digitaldark.ChambeaPe_Api.worker_data.dto.request.PortfolioRequestDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.dto.response.PortfolioResponseDTO;
import com.digitaldark.ChambeaPe_Api.worker_data.service.PortfolioService;
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

@Tag(name = "PortfolioController", description = "Controller for Portfolio")
@RestController
@RequestMapping("/api/v1/workers")
@CrossOrigin(origins = "*")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    //URL: http://localhost:8080/api/v1/workers/{id}/portfolios
    //Method: GET ALL
    @Operation(summary = "Get all Portfolios by Worker Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all Portfolios by Worker Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortfolioResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/{id}/portfolios")
    public ResponseEntity<List<PortfolioResponseDTO>> getAllPortfoliosByWorkerId(@PathVariable(value = "id") int userId) {
        return new ResponseEntity<List<PortfolioResponseDTO>>(portfolioService.getAllPortfoliosByWorkerId(userId), HttpStatus.OK);
    }

    //method: Get
    //URL: http://localhost:8080/api/v1/workers/{id}/portfolios/{id}
    @Operation(summary = "Get Portfolio by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Portfolio by Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortfolioResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/{id}/portfolios/{portfolioId}")
    public ResponseEntity<PortfolioResponseDTO> getPortfolioById(
            @PathVariable(value = "portfolioId") int portfolioId) {
        return new ResponseEntity<PortfolioResponseDTO>(portfolioService.getPortfolioById(portfolioId), HttpStatus.OK);
    }

    //method: Post
    //URL: http://localhost:8080/api/v1/workers/{id}/portfolios
    @Operation(summary = "Create Portfolio")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Portfolio created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortfolioResponseDTO.class)))
    @Transactional
    @PostMapping("/{id}/portfolios")
    public ResponseEntity<PortfolioResponseDTO> createPortfolio(@Valid @RequestBody PortfolioRequestDTO portfolio, @PathVariable(value = "id") int userId) {
        return new ResponseEntity<PortfolioResponseDTO>(portfolioService.createPortfolio(portfolio, userId), HttpStatus.CREATED);
    }

    //method: Delete
    //URL: http://localhost:8080/api/v1/workers/{id}/portfolios/{id}
    @Operation(summary = "Delete Portfolio by Id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning Portfolio deleted",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortfolioResponseDTO.class)))
    @Transactional
    @DeleteMapping("/{id}/portfolios/{portfolioId}")
    public ResponseEntity<?> deletePortfolio(
            @PathVariable(value = "id") int userId,
            @PathVariable(value = "portfolioId") int portfolioId) {
        portfolioService.deletePortfolioById(portfolioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
