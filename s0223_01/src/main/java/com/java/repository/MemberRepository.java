package com.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.dto.MemberDto;

//@Repository
// JpaRepository<사용객체, primary key 타입> :  
public interface MemberRepository extends JpaRepository<MemberDto, String> {

	// JPA는 select에서 리턴타입이 Optional 
	Optional<MemberDto> findByIdAndPw(String id, String pw);

	@Query(value = "select * from memberdto where id=? and pw=?",nativeQuery = true)
	Optional<MemberDto> selectLogin(String id, String pw);

	// save()를 만들지 않아도 저장진행
}
