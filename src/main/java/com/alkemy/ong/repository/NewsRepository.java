package com.alkemy.ong.repository;

import com.alkemy.ong.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("UPDATE news e SET e.active = 0 WHERE e.id = :id")
    @Modifying
    void softDelete(@Param("id") Long id);

}