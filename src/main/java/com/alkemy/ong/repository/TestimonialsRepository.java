package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialsRepository extends JpaRepository<Testimonial, Long> {
    @Query("UPDATE Testimonial t SET t.active = 0 WHERE t.id = :id")
    @Modifying
    void softDelete(@Param("id") Long id);
}
