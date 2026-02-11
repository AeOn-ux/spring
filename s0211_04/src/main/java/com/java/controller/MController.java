package com.java.controller;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;
import com.java.service.MService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MController {

	
	@Autowired HttpSession session;
	// @Autowired MService memberService = new MServiceImpl();
	@Autowired MService memberService;

	@GetMapping("/member/login")
	public String login(String flag, Model model) {
		model.addAttribute("flag", flag);
		
		return "login";
	}
	
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();//섹션모두삭제
		
		return "redirect:/?flag=0";
	}
	
	// 데이터 받는 법
	//1.HttpServletRequest
	//2.@RequestParam
	//3.객체 -> MemberDto에서 내려받는 객체
	@PostMapping("/member/login") //form
	public String login(MemberDto member, Model model) {
		
		
		System.out.println("id, pw : "+member.getId()+", "+member.getPw());
		// db 연결 - id, pw가 맞으면 1개 가져오기
		// MService에서 MemberDto의 데이터값을 받아 m값을 선언.
		MemberDto m = memberService.selectIdAndPw(member);
		
		if(m != null) {
			System.out.println("m : "+m.getId());
			session.setAttribute("session_id", m.getId());
			session.setAttribute("session_name", m.getName());
			return "redirect:/?flag=1";
		}else {
			System.out.println("아이디와 패스워드가 일치하지 않습니다.");
		}
		return "redirect:/member/login?flag=2";
		// return "redirect:/";
	}
	
	
	@GetMapping("/member/join01")
	public String join01() {
		return "join01";
	}
	
}
