package com.digitaldark.ChambeaPe_Api.user.repository;

import com.digitaldark.ChambeaPe_Api.user.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findById(int id);
    UsersEntity findByEmailAndPassword(String email, String password);

    List<UsersEntity> findAll();

    boolean existsById(int id);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);
    boolean existsByEmail(String email);
    UsersEntity findByEmail(String email);
}
