package com.alkemy.ong.repository;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.entity.Organization;
import com.alkemy.ong.entity.Slide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

    @Query("SELECT MAX(s.order) FROM Slide s WHERE s.organization = :organization")
    Integer getLastOrder(@Param("organization") Organization organization);

    @Query("SELECT s FROM Slide s WHERE s.organization = :idOrg ORDER BY s.order ASC")
    List<Slide> findByOrganizationIdOrderByOrderAsc(@Param("idOrg") Long id);

}
