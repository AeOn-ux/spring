package com.java.service;

import com.java.dto.MemberDto;

public interface MemberService {

	// 로그인
	MemberDto findByIdAndPw(MemberDto mDto);

	// 로그인2
	MemberDto selectLogin(MemberDto mDto);

	// 회원가입 저장
	void save(MemberDto mDto);

}
