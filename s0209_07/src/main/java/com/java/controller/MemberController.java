package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin() {
		return "member/doLogin";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "member/logout";
	}
	
	
}
