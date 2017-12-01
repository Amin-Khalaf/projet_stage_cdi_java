package com.umanteam.dadakar.transaction.back.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.umanteam.dadakar.transaction.back.dto.TransactionDTO;
import com.umanteam.dadakar.transaction.back.enums.TxState;

public interface ITransactionService {
	TransactionDTO addOrUpdate(TransactionDTO transactionDTO);
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
