package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity // 테이블생성
public class BoardDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;

	@Column(nullable = false, length = 100)
	private String btitle;
	@Lob // 대용량 문자열 clob
	private String bcontent;
	
	// @OneToMany, @ManyToMany, @ManyToOne // 한명의 user가 여러 개시글 작성
	@ManyToOne
	@JoinColumn(name="id")
	private MemberDto member;
	
	@ColumnDefault("0")
	private int bgroup;
	@ColumnDefault("0")	
	private int bstep;
	@ColumnDefault("0")	
	private int bindent;
	@ColumnDefault("0")	
	private int bhit;
	
	@Column(length = 100)
	private String bfile;
	@CreationTimestamp // 날짜 자동 생성
	private Timestamp bdate;
}
