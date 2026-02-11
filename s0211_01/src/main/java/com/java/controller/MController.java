package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

@Controller
public class MController {
	// @Autowired: Spring이 관리하는 'MemberService' 객체를 자동으로 연결(주입)
	@Autowired MemberService memberService;
	
	@GetMapping("/member/mlist")
	public String mlist(Model model) {
		List<MemberDto> list = memberService.selectAll();
		model.addAttribute("list", list);
		System.out.println("list 개수 : "+list.size());
		return "mlist";
		
	}
	
}
