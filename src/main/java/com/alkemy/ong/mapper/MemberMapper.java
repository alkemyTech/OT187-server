package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberDtoToMember(MemberDto memberDto);
    MemberDto memberToMemberDto(Member member);

    List<Member> memberDtoListToMemberList(List<MemberDto> memberDtoList);

    List<MemberDto> memberListToMemberDtoList(List<Member> memberList);

}
