package com.java.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EventDto {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 이벤트 고유 식별자

    @Column(nullable = false, length = 200)
    private String title;  // 이벤트 제목

    @Column(columnDefinition = "TEXT")
    private String description;  // 이벤트 상세 설명

    @Column(length = 255)
    private String location; // 이벤트가 열리는 장소

    @Column(nullable = false)
    private LocalDateTime startDateTime;  // 이벤트 시작일

    @Column(nullable = false)
    private LocalDateTime endDateTime;  // 이벤트 종료일

    @Column(nullable = false)
    private Integer imageUrl; // 배너이미지
    @Column(nullable = false)
    private Integer imageUrl2; // 배너이미지2

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 작성일

    @Column(nullable = false)
    private LocalDateTime updatedAt;  // 수정일
}
