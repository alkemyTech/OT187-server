package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("UPDATE Activity a SET a.deleted = 1 WHERE a.id = :id")
    @Modifying
    void softDelete(@Param("id") Long id);

    @Query("SELECT a FROM Activity a WHERE a.deleted = 0")
    List<Activity> findAllActive();
}
