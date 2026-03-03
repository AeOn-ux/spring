package com.java.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.MemberDto;
import com.java.dto.MemberPhone;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Controller // JSP페이지 리턴
//@RestController // 문자 리턴
public class MController {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	
	// 회원정보 수정
	@PostMapping("/member/mupdate")
	public String mupdate(MemberDto mDto, MemberPhone mPhone, Model model) {
		
		String id = (String)session.getAttribute("session_id");
		mDto.setId(id);
		
		String phone = String.format("%s-%s-%s", mPhone.getPhone1(), mPhone.getPhone2(), mPhone.getPhone3());
		mDto.setPhone(phone);
		
		System.out.println("controller phone : "+mDto.getPhone());
		
		// db저장 - update()
		memberService.update(mDto);
	
		return "redirect:/member/mlist";
	}
	
	// 회원정보 수정
	@GetMapping("/member/mupdate")
	public String mupdate(MemberDto mDto, Model model) {
		

		System.out.println("mview id : "+mDto.getId());
		
		MemberDto memberDto = memberService.findById(mDto.getId());
		
		model.addAttribute("member",memberDto);
		
		String[] phones = memberDto.getPhone().split("-");
		model.addAttribute("phones",phones);
		
		return "mupdate";
	}
	
	// 회원정보 상세보기
	@GetMapping("/member/mview")
	public String mview(MemberDto mDto, Model model) {
		System.out.println("mview id : "+mDto.getId());
		
		MemberDto memberDto = memberService.findById(mDto.getId());
		
		model.addAttribute("member",memberDto);
		
		return "mview";
	}
	
	// 회원삭제 start //-----------------------------------------------------------------
	
	@GetMapping("/member/delete")
	public String delete(MemberDto mDto) {
		System.out.println("controller id : "+mDto.getId());
		
		memberService.deleteById(mDto);
		
		return "redirect:/";
	}

	// 회원삭제 Ajax	
	@ResponseBody // 데이터로 전달
	@DeleteMapping ("/member/mdelete")
	public String mdelete(MemberDto mDto, Model model) {

		System.out.println("mdelete id : "+mDto.getId());
		
		memberService.deleteById(mDto);

		return "success";
	}
	
	// 회원삭제 end //-----------------------------------------------------------------
	
	
	
	// 회원가입 start //-----------------------------------------------------------------
	
	@GetMapping("/member/memberShip")
	public String memberShip(Model model) {
		return "memberShip";
	}
	
	// 회원가입 저장
	@PostMapping("/member/memberShip")
	public String memberShip(
			//@RequestParam(name="phone1", required = false) String phone1,
			//@RequestParam(name="phone2", required = false) String phone2,
			//@RequestParam(name="phone3", required = false) String phone3,
			MemberPhone mPhone,
			MemberDto mDto) {
		System.out.println("controller mdto : "+mDto.getId());
		String phone = String.format("%s-%s-%s", mPhone.getPhone1(), mPhone.getPhone2(), mPhone.getPhone3());
		mDto.setPhone(phone);
		System.out.println("controller phone : "+mDto.getPhone());
		
		// db저장
		memberService.save(mDto);
		
		return "redirect:/member/mlist";
	}
	// 회원가입 end //-----------------------------------------------------------------

	
	// 전체 회원 리스트
	@GetMapping("/member/mlist")
	public String mlist(Model model) {
		List<MemberDto> list = memberService.findAll();
		System.out.println("controller mlist : "+list.size());
		model.addAttribute("list",list);
		return "mlist";
	}
	
	//@ResponseBody // 문자형식으로 리턴
	@GetMapping("/member/login")
	public String login() {
		return "login";
		//return "문자를 리턴";
	}
	
	@PostMapping("/member/login")
	public String dologin(MemberDto mDto,
			@RequestParam(name="rememberId", required = false) String rememberId,
			HttpServletResponse response) {
		
		System.out.println("id : "+mDto.getId());
		System.out.println("pw : "+mDto.getPw());
		
		MemberDto memberDto = memberService.findByIdAndPw(mDto);
		
		Cookie cookie = new Cookie("cookie_id", mDto.getId());

		if(rememberId != null) {
			cookie.setMaxAge(60*60*24*30);
		}else {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		
	
		if(memberDto != null) {
			System.out.println("로그인이 되었습니다.");
			// 세션을 저장해서 메인으로 리턴
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			
			return "redirect:/";
			
		}else {
			System.out.println("로그인 또는 비밀번호 불일치!");
			// 로그인페이지로 리턴
			return "redirect:/member/login";	
		}
	}
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
