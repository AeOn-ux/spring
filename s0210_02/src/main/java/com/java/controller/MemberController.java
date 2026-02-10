package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
@Controller
public class MemberController {


	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/doLogin")
	public String login(MemberDto mdto, Model model) {
		System.out.println(String.format("%s,%s", 
				mdto.getId(), mdto.getPw()));
		model.addAttribute("member", mdto);
		return "doLogin";
	}	
	
	
	@GetMapping("/Join")
	public String Join() {
		return "Join";
	}
	
	@PostMapping("/doJoin")
	public String join(MemberDto mdto, Model model) {
		System.out.println(String.format("%s,%s,%s,%s,%s,%s,%s", 
				mdto.getId(), mdto.getPw(), mdto.getName(), mdto.getPhone(), 
				mdto.getEmail(), mdto.getGender(), mdto.getHobby()));
		model.addAttribute("member", mdto);
		return "doJoin";
	}	
	
	
	
}
