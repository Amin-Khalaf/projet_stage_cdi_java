package com.umanteam.dadakar.transaction.back.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.transaction.back.dto.TransactionDTO;
import com.umanteam.dadakar.transaction.back.entities.Transaction;
import com.umanteam.dadakar.transaction.back.enums.TxState;
import com.umanteam.dadakar.transaction.back.repository.TransactionRepository;
import com.umanteam.dadakar.transaction.back.service.interfaces.ITransactionService;

@Service("transactionService")
public class TransactionService implements ITransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	/* copy from TransactionDTO to Transaction */
	private Transaction transactionDTOToTransaction(TransactionDTO transactionDTO) {
		Transaction transaction = new Transaction();
		BeanUtils.copyProperties(transactionDTO, transaction);
		return transaction;
	}
	
	/* copy from Transaction to TransactionDTO */
	private TransactionDTO transactionToTransactionDTO(Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		BeanUtils.copyProperties(transaction, transactionDTO);
		return transactionDTO;
	}
	
	@Override
	public TransactionDTO addOrUpdate(TransactionDTO transactionDTO) {
		return transactionToTransactionDTO(transactionRepository.saveAndFlush(transactionDTOToTransaction(transactionDTO)));
	}

	@Override
	public void delete(Integer id) {
		transactionRepository.delete(id);
	}

	@Override
	public List<TransactionDTO> findAll() {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findAll();
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public TransactionDTO findById(Integer id) {
		Transaction transaction = transactionRepository.getOne(id);
		if(transaction != null) return transactionToTransactionDTO(transaction);
		return new TransactionDTO();
	}

	@Override
	public TransactionDTO findBytransactionNumber(String transactionNumber) {
		Transaction transaction = transactionRepository.findBytransactionNumber(transactionNumber);
		if(transaction != null) return transactionToTransactionDTO(transaction);
		return new TransactionDTO();
	}

	@Override
	public List<TransactionDTO> findByTransactionDate(LocalDateTime transactionDate) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findByTransactionDate(transactionDate);
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findBySenderId(String senderId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findBySenderId(senderId);
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByReceiverId(String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findByReceiverId(receiverId);
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByState(TxState state) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findByState(state);
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndSenderId(LocalDateTime transactionDate, String senderId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findByTransactionDateAndSenderId(transactionDate, senderId);
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndReceiverId(LocalDateTime transactionDate, String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findByTransactionDateAndReceiverId(transactionDate, receiverId);
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findBySenderIdAndReceiverId(String senderId, String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findBySenderIdAndReceiverId(senderId, receiverId);
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndState(LocalDateTime transactionDate, TxState state) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findByTransactionDateAndState(transactionDate, state);
		if(transactions != null) for(Transaction transaction: transactions) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

}
