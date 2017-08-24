package com.umanteam.dadakar.back.transaction.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.umanteam.dadakar.back.transaction.dto.TransactionDTO;
import com.umanteam.dadakar.back.transaction.enums.TxState;

public interface ITransactionService {
	TransactionDTO add(TransactionDTO transactionDTO);
	TransactionDTO update(TransactionDTO transactionDTO);
	void delete(Integer id);
	List<TransactionDTO> findAll();
	TransactionDTO findById(Integer id);
	TransactionDTO findBytransactionNumber(String transactionNumber);
	List<TransactionDTO> findByTransactionDate(LocalDateTime transactionDate);
	List<TransactionDTO> findBySenderId(String senderId);
	List<TransactionDTO> findByReceiverId(String receiverId);
	List<TransactionDTO> findByState(TxState state);
	List<TransactionDTO> findByTransactionDateAndSenderId(LocalDateTime transactionDate, String senderId);
	List<TransactionDTO> findByTransactionDateAndReceiverId(LocalDateTime transactionDate, String receiverId);
	List<TransactionDTO> findBySenderIdAndReceiverId(String senderId, String receiverId);
	List<TransactionDTO> findByTransactionDateAndState(LocalDateTime transactionDate, TxState state);
}
