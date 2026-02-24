package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import lombok.RequiredArgsConstructor;

// 참고)
// @RequiredArgsConstructor // final 검색 후 객체선언
// private final MemberService memberService;

@Controller
public class MController {
	
	@Autowired private MemberService memberService;
	
	@GetMapping("/member/memberShip")
	public String memberShip() {
		return "memberShip";
	}
	
	@PostMapping("/member/memberShip")
	public String memberShip(
			@RequestParam(name="phone1", required = false) String phone1,
			@RequestParam(name="phone2", required = false) String phone2,
			@RequestParam(name="phone3", required = false) String phone3,
			MemberDto mDto
			) {
		
		String phone = String.format("%s-%s-%s", phone1,phone2,phone3);
		mDto.setPhone(phone);

		System.out.println("phone : "+mDto.getPhone());
		System.out.println("name : "+mDto.getName());
		
		// db저장
		memberService.save(mDto);
		
		return "redirect:/";
	}
	
	
	// 로그인페이지 호출
	@GetMapping("/member/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/member/login")
	public String login(MemberDto mDto) {
		
		System.out.println("id : "+mDto.getId());
		System.out.println("pw : "+mDto.getPw());

		//MemberDto memberDto = memberService.findByIdAndPw(mDto);
		MemberDto memberDto = memberService.selectLogin(mDto);
		
		System.out.println("memberDto name : "+memberDto.getName());
		
		
		return "login";
	}
}
