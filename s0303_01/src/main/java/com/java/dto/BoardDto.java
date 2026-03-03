package com.java.dto;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardDto_seq") // oracle 시퀀스 생성
@SequenceGenerator(
		name = "boardDto_seq_generater", // generater 이름
		sequenceName="boardDto_seq", // 오라클테이블에서 시퀀스 이름
		initialValue = 106, // 시작번호
		allocationSize = 1 // 메모리 할당범위
		)

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BoardDto {
	
	@Id
	//GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator="boardDto_seq_generater")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardDto_seq_generater")
	private Integer bno;
	
	@Column(length = 1000, nullable = false)
	private String btitle;
	@Lob // 대용량데이터 - CLOB
	private String bcontent;
	
	@ManyToOne(fetch = FetchType.EAGER) // 데이터를 바로 불러옴
	@JoinColumn(name="id") //db에 저장되는 컬럼은 ID
	private MemberDto memberDto;

	private int bgroup;
	@ColumnDefault("0")
	private int bstep;
	@ColumnDefault("0")
	private int bindent;
	@ColumnDefault("0")
	private int bhit;
	@Column(length = 100)
	private String bfile;
	@CreationTimestamp
	private Timestamp bdate;
	
	// 하단 댓글 리스트 추가
	// 1개의 게시글은 여러개의 댓글을 가질 수 있음.
	@OneToMany(mappedBy = "boardDto", fetch=FetchType.EAGER,
			cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"boardDto"}) // 무한루프방지
	@OrderBy("cno desc") // cno 역순정렬
	private List<CommentDto> commentList;
	
	
	
}
