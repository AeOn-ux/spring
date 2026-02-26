package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.dto.BoardDto;

@Repository
public interface BoardRepository extends JpaRepository<BoardDto, Integer>{
	
	

}
