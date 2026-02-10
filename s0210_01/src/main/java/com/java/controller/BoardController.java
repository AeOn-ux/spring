package com.java.controller;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

	@GetMapping("/board")
	public String board() {
		return "board";
	}
	
	// @pathVariable 방법
	@GetMapping("/boardView/{bno}")
	public ModelAndView boardView(@PathVariable int bno) {
		
		BoardDto b = BoardDto.builder().bno(1).btitle("제목입니다.")
				.bcontent("내용입니다.").build();
		
		
		System.out.println("bno : "+bno);
		ModelAndView mv = new ModelAndView();
		mv.addObject("bno",bno);
		mv.setViewName("boardView");
		return mv;
	}
	
	
	
//	// 파라미터값으로 전달 -@RequestParam
//	@GetMapping("/boardView")
//	public ModelAndView boardView(Integer bno) {
//		System.out.println("bno : "+bno);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("bno",bno);
//		mv.setViewName("boardView");
//		return mv;
//	}
	
	// 4. 객체 타입
	@PostMapping("/board")
	public String board(BoardDto bdto, Model model) {
		model.addAttribute("board",bdto);
		return "doBoard";
	}
	
	
	
	
	
	
	// @RequestParam 방식 - 이름이 같으면 이름 생략 , @RequestParam 생략 가능
	// - 자동으로 형변환 가능 - 타입이 다른 경우는 null값일 때 에러 발생
	// 타입 변경시 null값 입력될 때, 디폴트값 설정 가능
	// @RequestParam(name="bno", defaultValue="1")
	// 3. 축약 방식
//	@PostMapping("/board")
//	public String board(
//			 int bno, String btitle, String bcontent, String id,
//			Model model) {
//		System.out.println(String.format("%d,%s,%s,%s",
//				bno,btitle,bcontent,id));
//		model.addAttribute("bno",bno);
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("id",id);
//		return "doBoard";
//	}
	
	
//	// 2. @RequestParam 방식
//		@PostMapping("/doBoard")
//		public String board(
//				@RequestParam("bno") String bno,
//				@RequestParam("btitle") String btitle,
//				@RequestParam("bcontent") String bcontent,
//				@RequestParam("id") String id,
//				Model model) {
//			System.out.println(String.format("%d,%s,%s,%s",
//					bno,btitle,bcontent,id));
//			model.addAttribute("bno",bno);
//			model.addAttribute("btitle",btitle);
//			model.addAttribute("bcontent",bcontent);
//			model.addAttribute("id",id);
//			return "doBoard";
//		}
	
	// 1. HttpServletRequest request 방식
//	@PostMapping("/board")
//	public String board(HttpServletRequest request, Model model) {
//		int bno = Integer.parseInt(request.getParameter("bno"));
//		String btitle = request.getParameter("btitle");
//		String bcontent = request.getParameter("bcontent");
//		String id = request.getParameter("id");
//		System.out.println(String.format("%d,%s,%s,%s",
//				bno,btitle,bcontent,id));
//		model.addAttribute("bno",bno);
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("id",id);
//		return "board";
//	}
	
}
