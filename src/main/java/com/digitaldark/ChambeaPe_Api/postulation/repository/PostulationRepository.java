package com.digitaldark.ChambeaPe_Api.postulation.repository;

import com.digitaldark.ChambeaPe_Api.post.model.PostsEntity;
import com.digitaldark.ChambeaPe_Api.postulation.model.PostulationsEntity;
import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostulationRepository extends JpaRepository<PostulationsEntity, Integer> {
    List<PostulationsEntity> findAllByPost(PostsEntity post);
    boolean existsById(int id);
    PostulationsEntity findById(int id);
    boolean existsByPostAndWorker(PostsEntity post, WorkerEntity worker);
    PostulationsEntity findByPostAndWorker(PostsEntity post, WorkerEntity worker);
}
