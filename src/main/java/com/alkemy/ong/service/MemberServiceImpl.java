package com.alkemy.ong.service;

import com.alkemy.ong.entity.Member;
import com.alkemy.ong.repository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRespository memberRespository;

    @Override
    public List<Member> findAll() {
        return memberRespository.findAll();
    }

    @Override
    public Member findById(Long id) {
        return memberRespository.findById(id).get();
    }

    @Override
    public Member save(Member member) {
        return memberRespository.save(member);
    }

    @Override
    public void deleteById(Long id) {
        memberRespository.deleteById(id);
    }
}
