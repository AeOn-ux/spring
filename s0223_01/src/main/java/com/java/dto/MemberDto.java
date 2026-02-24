package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder // 부분생성자
@AllArgsConstructor // 전체생성자
@NoArgsConstructor //기본생성자
@Setter
@Getter
@Entity // JPA로 테이블 자동생성
public class MemberDto {
	
	@Id //promary key등록
	private String id;
	@Column(nullable = false, length = 100)
	private String pw;
	@Column(nullable = false, length = 30)
	private String name;
	@Column(length = 13)
	private String phone;
	@Column(length = 40)
	private String email;
	@ColumnDefault("'남자'")	
	private String gender;
	@Column(length = 100)
	private String hobby;
	@CreationTimestamp // 시간자동생성 sysdate
	private Timestamp mdate;
}
