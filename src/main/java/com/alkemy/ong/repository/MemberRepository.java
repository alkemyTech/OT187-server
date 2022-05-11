package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("UPDATE Member m SET m.active = 0 WHERE m.id = :id")
    @Modifying
    void softDelete(@Param("id") Long id);

    @Query("SELECT m FROM Member m WHERE m.active = 1")
    Page<Member> findAll(Pageable pageable);

}
