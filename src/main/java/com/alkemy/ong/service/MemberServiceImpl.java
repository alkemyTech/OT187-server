package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.PageResponseDto;
import com.alkemy.ong.entity.Member;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.alkemy.ong.utility.Constantes.PAGE_SIZE;
import static com.alkemy.ong.utility.Constantes.PAGE_URL;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<MemberDto> getAll(Integer page) {

        if (page == null || page < 1) {
            throw new NotFoundException("Page must be greater than 0 and less than or equal to the total number of pages");
        }

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<Member> members = memberRepository.findAll(pageable);

        if (members.isEmpty()) {
            throw new NotFoundException("The requested page does not exist");
        }

        Page<MemberDto> memberDto = members.map(memberMapper::memberToMemberDto);

        return new PageResponseDto<>(memberDto, PAGE_URL);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundException("Member not found"));

        return memberMapper.memberToMemberDto(memberRepository.findById(id).get());

    }

    @Override
    @Transactional
    public MemberDto save(Member member) {
        return memberMapper.memberToMemberDto(memberRepository.save(member));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundException("Member not found"));

        memberRepository.softDelete(id);
    }
}
