package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRespository extends JpaRepository<Member,Long> {
}