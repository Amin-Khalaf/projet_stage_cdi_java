package com.umanteam.dadakar.back.transaction.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umanteam.dadakar.back.transaction.entities.Transaction;
import com.umanteam.dadakar.back.transaction.enums.TxState;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	Transaction findBytransactionNumber(String transactionNumber);
	List<Transaction> findByTransactionDate(LocalDateTime transactionDate);
	List<Transaction> findBySenderId(String senderId);
	List<Transaction> findByReceiverId(String receiverId);
	List<Transaction> findByState(TxState state);
	List<Transaction> findByTransactionDateAndSenderId(LocalDateTime transactionDate, String senderId);
	List<Transaction> findByTransactionDateAndReceiverId(LocalDateTime transactionDate, String receiverId);
	List<Transaction> findBySenderIdAndReceiverId(String senderId, String receiverId);
	List<Transaction> findByTransactionDateAndState(LocalDateTime transactionDate, TxState state);
}
