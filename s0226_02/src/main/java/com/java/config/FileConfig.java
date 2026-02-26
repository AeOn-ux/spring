package com.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// url로 들어오는 링크이 주소를 설정 폴더 위치에서 데이터를 찾는 설정
// url로 들
@Configuration
public class FileConfig implements WebMvcConfigurer {
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// http://localhost:8181/upload/
		registry.addResourceHandler("/upload/**")
		.addResourceLocations("file:///c:/upload/");
		
	}

}
