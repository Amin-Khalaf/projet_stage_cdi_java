package com.umanteam.dadakar.transaction.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.transaction.back.dto.TransactionDTO;

public interface ITransactionWebService {
	TransactionDTO add(TransactionDTO transactionDTO);
	TransactionDTO update(TransactionDTO transactionDTO);
	void delete(Integer id);
	ResponseEntity<List<TransactionDTO>> findAll();
	TransactionDTO findById(Integer id);
	TransactionDTO findBytransactionNumber(String transactionNumber);
	ResponseEntity<List<TransactionDTO>> findByTransactionDate(String transactionDate);
	ResponseEntity<List<TransactionDTO>> findBySenderId(String senderId);
	ResponseEntity<List<TransactionDTO>> findByReceiverId(String receiverId);
	ResponseEntity<List<TransactionDTO>> findByState(String state);
	ResponseEntity<List<TransactionDTO>> findByTransactionDateAndSenderId(String transactionDate, String senderId);
	ResponseEntity<List<TransactionDTO>> findByTransactionDateAndReceiverId(String transactionDate, String receiverId);
	ResponseEntity<List<TransactionDTO>> findBySenderIdAndReceiverId(String senderId, String receiverId);
	ResponseEntity<List<TransactionDTO>> findByTransactionDateAndState(String transactionDate, String state);
}
