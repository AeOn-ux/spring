package com.java.controller;

import java.lang.classfile.Attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MemberController {
	
	@Autowired MemberService memberService;

	// 로그인페이지 호출
	@GetMapping("/member/login")
		public String login() {
		return "login";
	}
	
	// 로그인 확인
	@PostMapping("/member/login")
	public String doLogin(MemberDto mdto) {
		System.out.println("id, pw : "+mdto.getId()+", "+mdto.getPw());
		// 로그인 1개 객체 가져오기
		MemberDto memberDto = memberService.selectLogin(mdto);
		if(memberDto != null) {
			System.out.println("id,pw 존재함");
		}else {
			System.out.println("id, pw 일치하지 않음.");
		}
		return "login";
	}
}
