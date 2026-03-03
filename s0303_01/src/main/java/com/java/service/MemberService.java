package com.java.service;

import java.util.List;

import com.java.dto.MemberDto;

public interface MemberService {

	// 로그인 확인
	MemberDto findByIdAndPw(MemberDto mDto);

	//전체회원정보
	List<MemberDto> findAll();

	// 회원가입 저장
	void save(MemberDto mDto);

	// 회원삭제
	void deleteById(MemberDto mDto);

	// 회원정보 상세보기
	MemberDto findById(String id);

	// 회원정보 수정 저장
	void update(MemberDto mDto);

}
