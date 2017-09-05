package com.umanteam.dadakar.msg.back;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.msg.back.entities.Message;
import com.umanteam.dadakar.msg.back.repository.MessageRepository;

@SpringBootApplication
public class DadakarBackMsgApplication implements CommandLineRunner {
	
	@Autowired
	private MessageRepository msgRepository;

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackMsgApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		loadMsg();
		
	}
	
	private void loadMsg() {
		
		for(int i = 0; i < 10; i++) {
			Message message = new Message("senderId" + i, "receiverId" + i, LocalDateTime.now(), "test de message " + i);
			message = msgRepository.save(message);
			System.out.println(message);
		}
		
	}
	
}
