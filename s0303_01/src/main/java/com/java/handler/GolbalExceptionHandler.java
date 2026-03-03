package com.java.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//@RestController
//@Controller
//@ControllerAdvice // 최상위 콘트롤러
public class GolbalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String exceptionMethod(IllegalArgumentException e, Model model) {
		//return e.getMessage();
		model.addAttribute("message",e.getMessage());
		return "error";
	}
	
//	@ExceptionHandler(NullPointerException.class)
//	public String illegalArgument(NullPointerException e) {
//		//return "숫자를 0으로 나눌 수 없숩니다.";
//		return "error";
//	}
}
