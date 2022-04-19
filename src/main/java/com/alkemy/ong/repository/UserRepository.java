package com.alkemy.ong.repository;

import com.alkemy.ong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
