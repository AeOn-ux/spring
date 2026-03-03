package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.service.BoardService;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;



@Controller
//@RestController // 데이터로 전달
public class BController {
	
	@Autowired BoardService boardService;
	@Autowired MemberService memberService;
	@Autowired HttpSession session;

	
	// 답변 수정 저장
	@PostMapping("/board/breply")
	public String breply(BoardDto bDto, Model model,
			@RequestPart(name="file") MultipartFile files) {
		
		System.out.println("breply bgroup : "+bDto.getBgroup());
		System.out.println("breply bstep : "+bDto.getBstep());
		
		// 파일 이름 저장
		if(!files.isEmpty()) {
			String fName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String refName = String.format("%s_%s", time, fName);
			System.out.println("파일이름 : "+fName );
			System.out.println("파일변경 이름 : "+refName );
			
			String fileUploadUrl = "c:/upload/";
			
			File f = new File(fileUploadUrl+refName);
			try {
				files.transferTo(f);
				bDto.setBfile(refName);  //변경된 파일이름을 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String id = (String)session.getAttribute("session_id");
		
		// 작성자의 ID정보들로 변경필요
		MemberDto memberDto = memberService.findById(id);
		bDto.setMemberDto(memberDto);
		
		// 글수정
		boardService.reply(bDto);
				
		return "redirect:/board/blist";
	}
	
	// 답변 수정
	@GetMapping("/board/breply")
	public String breply(Model model,
			@RequestParam(name="bno", required = false) Integer bno) {
		System.out.println("breply bno : "+bno);
		
		Map<String, Object> map = boardService.findById(bno);
		model.addAttribute("board",map.get("boardDto"));
		
		return "breply";
	}	
	
	
	// 게시글 수정저장
	@PostMapping("/board/bupdate")
	public String bupdate(BoardDto bDto, Model model,
			@RequestPart(name="file") MultipartFile files) {
		
		System.out.println("bupdate bno : "+bDto.getBno());
		System.out.println("bupdate bfile : "+bDto.getBfile());
		
		// 파일 이름 저장
		if(!files.isEmpty()) {
			String fName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String refName = String.format("%s_%s", time, fName);
			System.out.println("파일이름 : "+fName );
			System.out.println("파일변경 이름 : "+refName );
			
			String fileUploadUrl = "c:/upload/";
			
			File f = new File(fileUploadUrl+refName);
			try {
				files.transferTo(f);
				bDto.setBfile(refName);  //변경된 파일이름을 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 글수정
		boardService.update(bDto);
				
		return "redirect:/board/blist";
	}
	
	// 게시글 수정
	@GetMapping("/board/bupdate")
	public String bupdate(Model model,
			@RequestParam(name="bno", required = false) Integer bno) {
		System.out.println("bupdate bno : "+bno);
		
		Map<String, Object> map = boardService.findById(bno);
		model.addAttribute("board",map.get("boardDto"));
		//model.addAttribute("board",boardDto);
		
		return "bupdate";
	}	

	
	@ResponseBody // 데이터 전달
	@DeleteMapping("/board/bdelete")
	public String bdelete(Model model,
			@RequestParam(name="bno", required = false) Integer bno
			) {
		System.out.println("bdelete bno : "+bno);
		boardService.deleteById(bno);
		return "성공";
	}
	
	// 상세보기 페이지
	@GetMapping("/board/bview/{bno}")
	public String bview(Model model,
			@PathVariable(name="bno", required = false) Integer bno,
			@RequestParam(name="bno", required = false) String category,
			@RequestParam(name="bno", required = false) String search
			) {
		
		System.out.println("bview bno : "+bno);
		Map<String, Object> map = boardService.findById(bno);
		// 게시글 1개
		model.addAttribute("board",map.get("boardDto"));
		model.addAttribute("commentList",((BoardDto)(map.get("boardDto"))).getCommentList());
		// 하단댓글리스트
		model.addAttribute("commentList",map.get("commentList"));
		model.addAttribute("preBoard",map.get("preDto"));
		model.addAttribute("nextBoard",map.get("nextDto"));
		model.addAttribute("category", category);
		model.addAttribute("search", search);
		//model.addAttribute("board",boardDto);
		
		return "bview";
	}
	
	// 글쓰기
	@GetMapping("/board/bwrite")
	public String bwrite(Model model) {
		return "bwrite";
	}
	
	// 글쓰기 저장
	@PostMapping("/board/bwrite")
	public String bwrite(BoardDto bDto, Model model,
			@RequestPart(name="file") MultipartFile files) {
		// 1. 로그인한 사용자의 ID를 세션에서 가져옴
		String id = (String)session.getAttribute("session_id");
		System.out.println("bwrite title : "+bDto.getBtitle());
		
		// 2. 파일 처리 로직 시작
		if(!files.isEmpty()) { // 업로드된 파일이 실제 존재하는지 확인
			String fName = files.getOriginalFilename(); // 사용자의 컴퓨터에 있던 원래 파일명
			long time = System.currentTimeMillis(); // 현재 시간을 숫자로 변환 (중복 방지용)
			String refName = String.format("%s_%s", time, fName); // "시간_파일명" 형태로 새 이름 생성
			System.out.println("파일이름 : "+fName );
			System.out.println("파일변경 이름 : "+refName );
			
			String fileUploadUrl = "c:/upload/"; // 파일이 실제로 저장될 서버 내부 경로
			
			File f = new File(fileUploadUrl+refName); // 경로와 이름을 조합해 파일 객체 생성
			try {
				files.transferTo(f); // 임시 보관 중인 파일을 지정한 경로(c:/upload/)에 실제 저장
				bDto.setBfile(refName);  // DB에는 중복 방지를 위해 변경된 파일명을 저장
			} catch (Exception e) {
				e.printStackTrace();  // 파일 저장 중 에러 발생 시 로그 출력
			}
		}
		
		// 3. 작성자 정보 매칭
		//MemberDto객체를 검색해서 저장
		MemberDto memberDto = memberService.findById(id); // ID로 회원 정보를 DB에서 조회
		bDto.setMemberDto(memberDto); // 조회된 회원 정보를 게시글 정보에 포함시킴
		
		// 4. 서비스 호출 및 페이지 이동
		// 글쓰기 저장
		boardService.save(bDto);
		return "redirect:/board/blist";
	}
	
//	// 3. 전체게시글리스트 - PageableDefault
//		@ResponseBody // 데이터로 전달
//		@GetMapping("/board/blist")
//		public Page<BoardDto> blist(
//				@PageableDefault(page=0, size=10)
//				@SortDefaults({
//					@SortDefault(sort="bgroup", direction = Sort.Direction.DESC),
//					@SortDefault(sort="bstep", direction = Sort.Direction.ASC)
//				}) Pageable pageable,
//				Model model) {
//			Page<BoardDto> pageList = boardService.findAll(pageable);
//			return pageList;
//		}// blist-pageable
	
	
	// 2. 전체게시글리스트 - page, size
//	@ResponseBody // 데이터로 전달
	@GetMapping("/board/blist")
	public String blist(
			// 사용자가 ?page=5 라고 주소에 적으면 5를 가져오고, 아무것도 안 적으면 기본값 1을 씁니다.
			@RequestParam(name="page",defaultValue = "1") int page,
			@RequestParam(name="size",defaultValue = "10") int size,
			@RequestParam(name="category", required = false) String category,
			@RequestParam(name="search", required = false) String search,
			Model model) {
		Map<String, Object> map = null;
		if(search != null){
			// 전체 가져오기
			map = boardService.findAll(page, size);
		}else {
			// 검색
			System.out.println("검색 : "+category+","+search);
			map = boardService.findContaining(page, size, category, search);
		}
		model.addAttribute("map", map);
		return "blist";
	}// blist-pageable

		
	// 1. 전체게시글리스트 - list
//	@GetMapping("/board/blist")
//	public String blist(Model model) {
//		
//		//정렬
//		Sort sort = Sort.by(
//				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep")
////				Sort.Order.desc("btitle")
//				);
//		
//		List<BoardDto> list = boardService.findAll(sort);
//		
//		System.out.println("list size : "+list.size());
//		
//		model.addAttribute("list",list);
//		return "blist";
//	}
}

