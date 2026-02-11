package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MemberDao;
import com.java.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	// MemberDao dao = new MemberDao(); 라고 직접 만들지 않아도, 
    // Spring이 미리 만들어둔 객체를 '툭' 던져 넣어줍니다.
	@Autowired MemberDao memberDao;
	
	// @Override: 인터페이스(MemberService)에 선언된 메서드를 실제로 구현한다는 표시
	@Override
	public List<MemberDto> selectAll() {
		List<MemberDto> list = memberDao.selectAll();
		return list;
	}

}
