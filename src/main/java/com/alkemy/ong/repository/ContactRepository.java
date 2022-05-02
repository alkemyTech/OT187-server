package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    @Query("UPDATE Contact c SET c.active = 0 WHERE c.id = :id")
    @Modifying
    void softDelete(@Param("id") Long id);
}
