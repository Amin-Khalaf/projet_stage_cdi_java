package com.umanteam.dadakar.admin.front;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootApplication
public class DadakarFrontAdminApplication  {

	public static String tokenValue = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJhNWY0ZWVmNy1kNDg4LTQzOWUtOGI3Ni03MDY5MDczYzJmMDEiLCJzdWIiOiJ1c2VyMiIsImlhdCI6MTUwNTEyNTk4MCwiZXhwIjozMDAxNTA1MTI1OTgwfQ.O4hvnT9IV3Q_X9y5Pp2Qr4pPvdaXXAL2-Wmt2paUQJAAIeUD5Y0BxHXwJs7kKtAtPViMF0ugee5N-D7roRh8Zw";
	
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
