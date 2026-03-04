package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.CommentDto;
import com.java.service.CommentService;

import jakarta.servlet.http.HttpSession;


@Controller
public class CommentController {

	@Autowired CommentService commentService;
	@Autowired HttpSession session;
	
	@ResponseBody
	@PostMapping("/comment/save")
	public CommentDto save(CommentDto cdto,
			@RequestParam(name="bno", defaultValue = "1") int bno,
			@RequestParam(name="day", defaultValue = "1") String day
			) {
		System.out.println("controller ccontent : "+cdto.getCcontent());
		System.out.println("controller bno : "+bno);
		System.out.println("controller cno : "+cdto.getCno());
		System.out.println("controller day : "+day);
		
//		// service 전달
//		CommentDto commentDto = commentService.save(cdto, bno);
		
		CommentDto commentDto = new CommentDto();
		
		return commentDto;
	}
	
}
