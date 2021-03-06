package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.entity.Member;

public interface MemberService {

    MemberDto findById(Long id);

    MemberDto save(MemberDto memberDto);

    void deleteById(Long id);

    PageResponseDto<MemberDto> getAll(Integer page);

    MemberDto updateMemberById(Long id, MemberDto dto);
}
