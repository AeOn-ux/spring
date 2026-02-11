package com.java.service;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.mapper.MMapper;

@Service
public class MServiceImpl implements MService {

	//@Component, @Controller, @Service, @Repository, @Configuration @Bean - 자동지원
	//@Mapper - MyBatis 설치해야만 생김.
	@Autowired MMapper mmapper; 
	
	
	@Override
	public MemberDto selectIdAndPw(MemberDto member) {
		String id = member.getId();
		String pw = member.getPw();
		System.out.println("kkk"+id);
		MemberDto m = mmapper.selectIdAndPw(id,pw);
		return m;
	}

	
	
}
