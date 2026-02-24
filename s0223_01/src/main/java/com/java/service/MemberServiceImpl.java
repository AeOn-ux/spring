package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberRepository memberRepository;

	@Override // 로그인
	public MemberDto findByIdAndPw(MemberDto mDto) {
		
		// 검색해서 데이터가 있을 경우 : memberDto 객체를 가져옴
		//       데이터가 없을 경우 : memberDto 빈객체를 생성해서 리턴
		MemberDto memberDto = 
				memberRepository.findByIdAndPw(mDto.getId(),mDto.getPw())
				.orElseGet(()->{return new MemberDto();});  // 없을 경우 빈객체를 생성해서 리턴				
				//.orElseThrow(()->{return new IllegalArgumentException(); }); // 없을경우 예외처리 해서 리턴
				//.get(); // 없을 경우 에러처리 

		return memberDto;
	}

	@Override // 로그인
	public MemberDto selectLogin(MemberDto mDto) {
		MemberDto memberDto = memberRepository.selectLogin(mDto.getId(),mDto.getPw()).get();
		return memberDto;
	}

	@Override // 회원가입저장
	public void save(MemberDto mDto) {
		memberRepository.save(mDto);
		
	}
	
}
