package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Slide;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlideRepository {

    @Query(nativeQuery = true, value = "SELECT * FROM slides WHERE organization = ?1 ORDER BY order_number DESC")
    List<Slide> findSlideByOrganizationId(String organizationId);

    @Query(nativeQuery = true, value = "SELECT max(order_number) FROM slides s")
    int getMaxOrder();

    Optional<Slide> findById(Long id);

    void softDelete(Long id);

    Slide save(Slide slide);

    List<Slide> findAll();

}
