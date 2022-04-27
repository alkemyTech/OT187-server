package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    void softDelete(@Param("id") Long id);

}
