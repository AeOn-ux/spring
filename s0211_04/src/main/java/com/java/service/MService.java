package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dto.MemberDto;

public interface MService {

	
	
	
	MemberDto selectIdAndPw(MemberDto member);

}
