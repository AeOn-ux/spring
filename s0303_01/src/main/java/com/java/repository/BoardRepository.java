package com.java.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;

public interface BoardRepository extends JpaRepository<BoardDto, Integer> {

	// 답변
	@Modifying  // Query 사용시 update, delete 실행할때  @Modifying, @Transactional 사용  
	@Transactional // 메소드 완료시 기존의 연속성 context가 수정되면 db에 자동반영	
	@Query(value = "update boarddto set bstep=bstep+1 where bgroup = ? and bstep > ? ", nativeQuery = true)
	void replyBstepUp(int bgroup, int bstep);
//	
//	@Modifying  // Query 사용시 update, delete 실행할때  @Modifying, @Transactional 사용  
//	@Transactional // 메소드 완료시 기존의 연속성 context가 수정되면 db에 자동반영		
//	@Query(value = "update boarddto set bstep = bstep+1 where bgroup = :bgroup  and bstep > :bstep ", nativeQuery = true)
//	void replyBstepUp(@Param("bgroup") int bgroup, @Param("bstep") int bstep);

    // 이전글
	@Query(value = "select * from boarddto where bno = (\r\n"
			+ "select pre_bno from (select bno,lag(bno,1,-1) over(order by bgroup desc,bstep asc) pre_bno from boarddto) where bno = ? \r\n"
			+ ")", nativeQuery = true)
	Optional<BoardDto> findByPre(Integer bno);

	// 다음글
	@Query(value="select * from boarddto where bno = (\r\n"
			+ "select next_bno from (select bno,lead(bno,1,-1) over(order by bgroup desc,bstep asc) next_bno from boarddto) where bno = ? \r\n"
			+ ")", nativeQuery = true)
	Optional<BoardDto> findByNext(Integer bno);

	// 검색 게시글 리스트 - 제목검색
	Page<BoardDto> findByBtitleContaining(String btitle, Pageable pageable);
	// 검색 게시글 리스트 - 내용검색
	Page<BoardDto> findByBcontentContaining(String bcontent, Pageable pageable);
	// 검색 게시글 리스트 - 제목, 내용 검색
	Page<BoardDto> findByBtitleContainingOrBcontentContaining(String btitle, String bcontent, Pageable pageable);
	
}
