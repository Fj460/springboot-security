package com.practice.Dockerize.repositories;

import com.practice.Dockerize.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository <User, UUID> {

   User findByName(String name);
}
