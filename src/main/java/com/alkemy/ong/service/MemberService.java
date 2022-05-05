package com.alkemy.ong.service;

import com.alkemy.ong.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> findAll();

    Member findById(Long id);

    Member save(Member member);

    void deleteById(Long id);
}
