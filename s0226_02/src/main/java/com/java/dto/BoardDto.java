package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@GeneratedValue(strategy = GenerationType.SEQUENCE,
//generator = "boardDto_seq_generater") // oracle 시퀀스
@SequenceGenerator( // boarddto 100개 게시글을 할당
		name="boardDto_seq_generater", // gemerator 이름
		sequenceName = "boardDto_seq", // 오라클 테이블에서 시퀀스이름
		initialValue = 101,  // 시작번호
		allocationSize = 1) // 메모리 할범위


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BoardDto {

	@Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // db 시퀀스
	@GeneratedValue(generator="boardDto_seq_generater")
	private Integer bno;
	@Column(length=1000, nullable = false)
	private String btitle;
	@Lob // 대용량 데이터 = CLOB
	private String bcontent;
	
	// private String id; // member Primary key -> foreign key 사용
	// @OneToMany // 1개 게시글은 여러명이 소유함. 
	// 연관관계가 형성이 됨. Foreign Key를 구성하게 됨.
	// @ManyToMany // 여러명 회원이 여러개의 글을 작성할 수 있음. -하단댓글
	@ManyToOne(fetch = FetchType.EAGER) // 1명 회원은 여러개 게시글을 작성할 수 잇음.
	@JoinColumn(name="id") // db에 저장하는 컬럼 id
	private MemberDto memberDto; // 객체를 넣어서 저장해야함.
	@Column
	private int bgroup;
	@ColumnDefault("0")
	private int bstep;
	@ColumnDefault("0")
	private int bindent;
	@ColumnDefault("0")
	private int bhit;
	@Column(length=100)
	private String bfile;
	@CreationTimestamp
	private Timestamp bdate;
	
}
