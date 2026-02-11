package com.java.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.MemberDto;

@Mapper
public interface MMapper {

	MemberDto selectIdAndPw(String id, String pw);


}
