package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;

@Controller
public class BoardController {

	@GetMapping("/Board")
	public String Board() {
		return "Board";
	}
	
	@PostMapping("/Board")
	public String board(BoardDto bdto, Model model) {
		System.out.println(String.format("%s,%s,%s,%s", 
				bdto.getId(), bdto.getBtitle(), bdto.getBcontent(), bdto.getBno()));
		model.addAttribute("board", bdto);
		return "doBoard";
	}

	
	
}
