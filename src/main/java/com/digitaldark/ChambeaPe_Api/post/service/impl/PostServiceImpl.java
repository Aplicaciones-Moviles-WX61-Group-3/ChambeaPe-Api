package com.digitaldark.ChambeaPe_Api.post.service.impl;

import com.digitaldark.ChambeaPe_Api.post.dto.request.PostRequestDTO;
import com.digitaldark.ChambeaPe_Api.post.dto.response.PostResponseDTO;
import com.digitaldark.ChambeaPe_Api.post.model.PostsEntity;
import com.digitaldark.ChambeaPe_Api.post.repository.PostRepository;
import com.digitaldark.ChambeaPe_Api.post.service.PostService;
import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ResourceNotFoundException;
import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import com.digitaldark.ChambeaPe_Api.user.repository.EmployerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl  implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private DateTimeEntity dateTimeEntity;

    /**/
    @Override
    public PostResponseDTO getPostById(int id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post not found");
        }

        PostsEntity postEntity = postRepository.findById(id);

        return modelMapper.map(postEntity, PostResponseDTO.class);
    }

    @Override
    public  List<PostResponseDTO> getAllPosts(){
        List<PostsEntity> postEntities = postRepository.findAll();

        return postEntities.stream()
                .map(postEntity -> modelMapper.map(postEntity, PostResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public  List<PostResponseDTO> getAllPostsByEmployerId(int employerId){
        EmployerEntity employerEntity = employerRepository.findById(employerId);

        List<PostsEntity> postEntities = postRepository.findByEmployer(employerEntity);

        return postEntities.stream()
                .map(postEntity -> modelMapper.map(postEntity, PostResponseDTO.class))
                .collect(Collectors.toList());
    }
    /**/
    @CrossOrigin(origins = "*")
    @Override
    public PostResponseDTO createPost(PostRequestDTO post, int employerId) {
        if(!employerRepository.existsById(employerId)) {
            throw new ResourceNotFoundException("Employer not found");
        }
        PostsEntity postEntity = modelMapper.map(post, PostsEntity.class);
        postEntity.setEmployer(employerRepository.findById(employerId));

        postEntity.setIsActive( (byte) 1);
        postEntity.setDateCreated(dateTimeEntity.currentTime());
        postEntity.setDateUpdated(dateTimeEntity.currentTime());

        System.out.println(postEntity);
        postRepository.save(postEntity);

        return modelMapper.map(postEntity, PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO updatePost(int id, PostRequestDTO post) {
        if(!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post not found");
        }
        PostsEntity postEntity = postRepository.findById(id);
        modelMapper.map(post, postEntity);
        postEntity.setDateUpdated(dateTimeEntity.currentTime());
        postEntity.setId(id);

        postRepository.save(postEntity);

        return modelMapper.map(postEntity, PostResponseDTO.class);
    }

    @Override
    public void deletePost(int id){
        if(!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post not found");
        }

        postRepository.deleteById(id);
    }


}
