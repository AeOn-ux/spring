package com.java.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.controller.BController;
import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

   

	@Autowired BoardRepository boardRepository;

   
	
	// 게시판
//	@Override
//	public List<BoardDto> findAll(Sort sort) {
//		
//		List<BoardDto> list = boardRepository.findAll(sort);
//
//		return list;
//	}

	// 글쓰기 저장
	@Transactional // 메소드 완료시 기존의 연속성 context가 수정되면 db에 자동반영
	@Override 
	public void save(BoardDto bDto) {
		
		//Repository저장 시 객체를 리턴해줌
		BoardDto boardDto = boardRepository.save(bDto);
		boardDto.setBgroup(boardDto.getBno());
		//boardRepository.save(boardDto);  // @Transactional 

	}

	// 게시글 1개 가져오기
	@Transactional(readOnly = true) // 정합성을 유지
	@Override
	public Map<String, Object> findById(Integer bno) {
		
		Map<String, Object> map = new HashMap<>();
		
		// 이전글
		BoardDto preDto = boardRepository.findByPre(bno).orElse(null);
		// 다음글
		BoardDto nextDto = boardRepository.findByNext(bno).orElse(null);
		// 해당글
		BoardDto boardDto = boardRepository.findById(bno).orElse(null);
		
		System.out.println("boardDto commentList 개수 : "+boardDto.getCommentList().size());
		map.put("commentList", boardDto.getCommentList());
		map.put("preDto", preDto);
		map.put("nextDto", nextDto);
		map.put("boardDto", boardDto);
		map.put("boardDto", boardDto);
		// 조회수 증복방지 cookie, session, db 등록
		boardDto.setBhit(boardDto.getBhit()+1);
		
		return map;
	}

	// 게시글 삭제
	@Override
	public void deleteById(Integer bno) {
		boardRepository.deleteById(bno);
	}

	// 게시글 수정 저장
	@Transactional // 메소드 완료시 기존의 연속성 context가 수정되면 db에 자동반영	
	@Override
	public void update(BoardDto bDto) {
		BoardDto boardDto = boardRepository.findById(bDto.getBno()).get();
		boardDto.setBtitle(bDto.getBtitle());
		boardDto.setBcontent(bDto.getBcontent());
		boardDto.setBfile(bDto.getBfile());
		boardDto.setBdate(new Timestamp(System.currentTimeMillis()));
		
//		boardRepository.save(boardDto); // @Transactional 자동으로 처리됨
	}

	// 답변달기 저장
	@Transactional // 메소드 완료시 기존의 연속성 context가 수정되면 db에 자동반영	
	@Override
	public void reply(BoardDto bDto) {
		// 1.부모의 bgroup에서 부모보다 큰 bstep에 1을 증가
		boardRepository.replyBstepUp(bDto.getBgroup(),bDto.getBstep());

		// 부모의 bgroup에서 부모보다 큰 bstep에 1을 증가
		bDto.setBgroup(bDto.getBgroup());
		bDto.setBstep(bDto.getBstep()+1);
		bDto.setBindent(bDto.getBindent()+1);		
		
		// 2. 
		boardRepository.save(bDto);
	}

	@Override
	public Map<String, Object> findAll(int page,int size) {
		
		// 1. 정렬 규칙 설정
		// 정렬 - bgroup:역순, bstep:순차
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep")
				);
		// 2. 페이징 정보 객체 생성
	    // 사용자가 요청한 page에서 1을 빼는 이유는 DB(JPA)가 페이지를 0부터 세기 때문
		// (예: 사용자가 1페이지 요청 -> DB에는 0페이지 가져오라고 전달)
		// Pageable - 현재페이지, 사이즈크기, 정렬, pageable은 0부터 시작함.
		Pageable pageable = PageRequest.of(page-1, size, sort);
		// 3. DB에서 데이터 가져오기
		// Page타입으로 리턴해서 받음.
		Page<BoardDto> pageList = boardRepository.findAll(pageable);
//		// 일반적인 형태
//		List<BoardDto> list = boardRepository.findAll();
		
		// 하단넘버링에 필요한 페이지를 구함
		int maxPage = pageList.getTotalPages(); // 마지막페이지 / 하단 넘버링(1~10, 11~20...)을 위한 계산
		int startPage = ((page)/10)*10+1; // 하단넘버링 시작번호 / 하단에 보여줄 시작 번호 계산 (예: 1, 11, 21...)
		int endPage = Math.min(startPage+10-1, maxPage); //하단넘버링 마지막 번호 / 하단에 보여줄 끝 번호 계산 (예: 10, 20, 30...)
		// 실제 게시글 데이터 추출
		List<BoardDto> list = pageList.getContent(); // 게시글 내용 - 하단 댓글 내용 추가
		
		Map<String, Object> map = new HashMap<>();
		map.put("page", page); // pageable = 0부터 시작함.
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("list", list);
		// 하단 넘버링에 필요한 데이터
		System.out.println("page"+page);
		return map;
	}// findAll

	// 검색 게시글 리스트
	@Override
	public Map<String, Object> findContaining(int page, int size, String category, String search) {
		// 정렬 - bgroup:역순, bstep:순차
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep")
				);
		Pageable pageable = PageRequest.of(page-1, size, sort);
		// Page 타입으로 리턴해서 받음
		Page<BoardDto> pageList = null;
		
		// search에 데이터가 있는 경우
		// 검색 - findByBtitleContaining(btitle)
	    if(category==null) {
	    	pageList = boardRepository.findAll(pageable);
	    }else if(category.equals("all")) {
	    	pageList = boardRepository.findByBtitleContainingOrBcontentContaining(search, search, pageable);
	    }else if(category.equals("btitle")) {
	    	pageList = boardRepository.findByBtitleContaining(search,pageable);
	    }else if(category.equals("bcontent")) {
	    	pageList = boardRepository.findByBcontentContaining(search,pageable);
	    }
		
	 // 하단넘버링에 필요한 페이지를 구함
		int maxPage = pageList.getTotalPages(); // 마지막페이지 / 하단 넘버링(1~10, 11~20...)을 위한 계산
		int startPage = ((page)/10)*10+1; // 하단넘버링 시작번호 / 하단에 보여줄 시작 번호 계산 (예: 1, 11, 21...)
		int endPage = Math.min(startPage+10-1, maxPage); //하단넘버링 마지막 번호 / 하단에 보여줄 끝 번호 계산 (예: 10, 20, 30...)
		// 실제 게시글 데이터 추출
		List<BoardDto> list = pageList.getContent(); // 게시글내용
		
		Map<String, Object> map = new HashMap<>();
		map.put("page", page); // pageable = 0부터 시작함.
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("list", list);
		map.put("category", category);
		map.put("search", search);
	    
		return map;
	}
	

}
