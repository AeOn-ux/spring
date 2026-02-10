package com.java.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MemberController {

	@GetMapping("/login")
	public String login(Integer flag, Model model) {
		System.out.println("flag : "+flag);
		model.addAttribute("flag", flag);
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		// jsp -> request.setAttibute()
		// spring -> model.addAttibute()
		if(id.equals("aaa")&&pw.equals("1111")) {
			return "redirect:/?flag=1";
		}else {
			return "redirect:/login?flag=2";
		
		}
//		model.addAttribute("id",id);
//		model.addAttribute("pw",pw);
//		return "doLogin";
	}// login
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/mUpdate")
	public String mUpdate(Model model) {
		MemberDto mdto = new MemberDto("aaa","1111","홍길동",
				"010-0000-0000","aaa@naver.com","남자","게임,산책");
		model.addAttribute("member", mdto);
				
		
		return "mUpdate";
	}
	
	@PostMapping("/dojoin")
	public String join (HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name= request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String gender= request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");
		String hobby = Arrays.toString(hobbys);
		
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		System.out.println("name : "+name);
		System.out.println("email : "+email);
		System.out.println("phone : "+phone);
		System.out.println("gender : "+gender);
		System.out.println("hobby : "+hobby);
		
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		model.addAttribute("name",name);
		model.addAttribute("email",email);
		model.addAttribute("phone",phone);
		model.addAttribute("gender",gender);
		model.addAttribute("hobby",hobby);
		return "dojoin";
	}
	
}
