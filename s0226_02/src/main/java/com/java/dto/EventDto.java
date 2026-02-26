package com.java.dto;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDto {

	private Integer eno; // 번호
	private String etitle; // 제목
	private String econtent; // 본문
	private String eimageb; // 이미지 경로 (배너)
	private String eimagec; // 이미지 경로 (컨텐츠)
	private String efile; // 첨부파일
	private Timestamp startDate; // 이벤트 시작일
	private Timestamp endDate;   // 이벤트 종료일
	private Timestamp ecrdate; // 작성일
	private Timestamp eupdate; // 수정일
	private Integer ehit;   // 조회수
	
}
