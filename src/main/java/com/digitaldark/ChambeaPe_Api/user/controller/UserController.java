package com.digitaldark.ChambeaPe_Api.user.controller;

import com.digitaldark.ChambeaPe_Api.user.dto.request.UserLoginDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.request.UserRequestDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.response.UserResponseDTO;
import com.digitaldark.ChambeaPe_Api.user.model.UsersEntity;
import com.digitaldark.ChambeaPe_Api.user.service.UserService;
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


@Tag(name = "UserController", description = "Controller to handle Users")
@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:4200") // Puerto de Angular
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;


    //URL: http://localhost:8080/api/v1/users/{id}
    //Method: GET
    @Operation(summary = "Get user by id")
    @ApiResponse(responseCode = "200",
            description = "Successful operation, returning user by id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable int id) {
        return new ResponseEntity<UserResponseDTO>(userService.getUser(id), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/users
    //Method: GET
    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200",
            description = "Successful operation, returning all users",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return new ResponseEntity<List<UserResponseDTO>>(userService.getAllUsers(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/users/login
    //Method: GET
    @Operation(summary = "Get user by email and password")
    @ApiResponse(responseCode = "200",
            description = "Successful operation, returning user by email and password",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponseDTO.class)))
    @Transactional(readOnly = true)
    @PostMapping("/users/login")
    public ResponseEntity<UserResponseDTO> getUserByEmailAndPass(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<UserResponseDTO>(userService.getUserEmailAndPass(userLoginDTO), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/users
    //Method: POST
    @Operation(summary = "Create user")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, user created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UsersEntity.class)))
    @Transactional
    @PostMapping("/usersv1")
    public ResponseEntity<UsersEntity> createUser(@RequestBody UsersEntity user) throws MessagingException, IOException {

        System.out.println("Se creo un usuario" + user);

        return new ResponseEntity<UsersEntity>(userService.createUser(user), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/v1/users
    //Method: POST
    @Operation(summary = "Create user DTO")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, user DTO created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponseDTO.class)))
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> createUserDTO(@Valid @RequestBody UserRequestDTO user) throws MessagingException, IOException {

        System.out.println("Se creo un usuario: " + user);

        return new ResponseEntity<UserResponseDTO>(userService.createUserDTO(user), HttpStatus.CREATED);
    }
}
