package com.java.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import com.java.dto.BoardDto;

public interface BoardService {

	// 전체 게시글 리스트
	List<BoardDto> findAll(Sort sort);

	// 글쓰기 저장
	void save(BoardDto bdto);

}
