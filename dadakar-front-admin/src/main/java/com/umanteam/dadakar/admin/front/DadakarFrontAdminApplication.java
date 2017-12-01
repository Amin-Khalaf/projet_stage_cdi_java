package com.umanteam.dadakar.admin.front;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootApplication
public class DadakarFrontAdminApplication  {
	
	public static String getToken(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		String token = session.getAttribute("ddkt").toString();
		return token;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DadakarFrontAdminApplication.class, args);
	}

}
