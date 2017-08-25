package com.umanteam.dadakar.transaction.back;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.transaction.back.entities.Transaction;
import com.umanteam.dadakar.transaction.back.enums.TxState;
import com.umanteam.dadakar.transaction.back.repository.TransactionRepository;

@SpringBootApplication
public class DadakarBackTransactionApplication implements CommandLineRunner {
	
	@Autowired
	private TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackTransactionApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		TransactionTest();
	}
	
	private void TransactionTest() {
		transactionRepository.deleteAll();
		for(int i = 0; i < 10; i++) {
			Transaction transaction = new Transaction("1F44C" + i, LocalDateTime.of(2017, 8, 23, 16, 57, i*2, 0), "55BF" + i, "66AE" + i, TxState.DONE);
			transaction = transactionRepository.saveAndFlush(transaction);
			System.out.println(transaction);
		}
	}
}
