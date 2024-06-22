package com.digitaldark.ChambeaPe_Api.post.controller;

import com.digitaldark.ChambeaPe_Api.post.dto.request.PostRequestDTO;
import com.digitaldark.ChambeaPe_Api.post.dto.response.PostResponseDTO;
import com.digitaldark.ChambeaPe_Api.post.service.PostService;
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

@Tag(name = "PostController", description = "Controller to handle Post")
@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:4200") // Puerto de Angular
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    private PostService postService;

    //URL: http://localhost:8080/api/v1/posts
    //Method: GET
    @Operation(summary = "Get all posts")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all posts",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PostResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        return new ResponseEntity<List<PostResponseDTO>>(postService.getAllPosts(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/posts/{id}
    //Method: GET
    @Operation(summary = "Get post by id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning post by id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PostResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable("id") int id) {
        return new ResponseEntity<PostResponseDTO>(postService.getPostById(id), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/employers/{employerId}/posts
    //Method: GET
    @Operation(summary = "Get all posts by employer id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning all posts by employer id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PostResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/employers/{employerId}/posts")
    public ResponseEntity<List<PostResponseDTO>> getAllPostsByEmployerId(@PathVariable("employerId") int employerId) {
        return new ResponseEntity<List<PostResponseDTO>>(postService.getAllPostsByEmployerId(employerId), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/employers/{employerId}/posts
    //Method: POST
    @Operation(summary = "Create a new post")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, returning the new post",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PostResponseDTO.class)))
    @Transactional
    @PostMapping("employers/{employerId}/posts")
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO post, @PathVariable("employerId") int employerId) {
        return new ResponseEntity<PostResponseDTO>(postService.createPost(post, employerId), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/v1/posts/{id}
    //Method: DELETE
    @Operation(summary = "Delete post by id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, post deleted",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PostResponseDTO.class)))
    @Transactional
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Object> deletePost( @PathVariable("id") int id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/posts/{id}
    //Method: PUT
    @Operation(summary = "Update post by id")
    @ApiResponse(responseCode = "201",
            description = "Successful operation, post updated",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PostResponseDTO.class)))
    @Transactional
    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable("id") int id,@Valid @RequestBody PostRequestDTO post) {
        return new ResponseEntity<PostResponseDTO>(postService.updatePost(id, post), HttpStatus.OK);
    }

}
