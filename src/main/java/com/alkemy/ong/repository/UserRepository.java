package com.alkemy.ong.repository;

import com.alkemy.ong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("UPDATE User u SET u.active = 0 WHERE u.id = :id")
    @Modifying
    void softDelete(@Param("id") Integer id);

    Optional<User> findByEmail(String email);
}
