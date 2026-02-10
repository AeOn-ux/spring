package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;

@Controller
public class FController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	
	
	
	
	
}
