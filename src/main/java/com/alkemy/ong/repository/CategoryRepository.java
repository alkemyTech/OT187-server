package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("UPDATE Category c SET c.active = 0 WHERE c.id = :id")
    @Modifying
    void softDelete(@Param("id") Long id);

}
