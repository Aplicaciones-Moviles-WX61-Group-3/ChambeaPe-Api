package com.digitaldark.ChambeaPe_Api.post.repository;

import com.digitaldark.ChambeaPe_Api.post.model.PostsEntity;
import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostsEntity, Integer>{
    PostsEntity findById(int id);
    boolean existsById(int id);
    List<PostsEntity> findByEmployer(EmployerEntity employer);
}
