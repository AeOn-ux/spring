package com.java.controller;

import org.springframework.stereotype.Service;

@Service
public class TV2 implements Product{
	public String getName() {
		String name = "삼성tv버전2";
		return name;
	}
		
}
