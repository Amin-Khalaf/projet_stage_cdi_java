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
		for(Transaction transaction: transactionRepository.findAll()) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public TransactionDTO findById(Integer id) {
		return transactionToTransactionDTO(transactionRepository.getOne(id));
	}

	@Override
	public TransactionDTO findBytransactionNumber(String transactionNumber) {
		return transactionToTransactionDTO(transactionRepository.findBytransactionNumber(transactionNumber));
	}

	@Override
	public List<TransactionDTO> findByTransactionDate(LocalDateTime transactionDate) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByTransactionDate(transactionDate)) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findBySenderId(String senderId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findBySenderId(senderId)) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByReceiverId(String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByReceiverId(receiverId)) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByState(TxState state) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByState(state)) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndSenderId(LocalDateTime transactionDate, String senderId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByTransactionDateAndSenderId(transactionDate, senderId)) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndReceiverId(LocalDateTime transactionDate, String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByTransactionDateAndReceiverId(transactionDate, receiverId)) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findBySenderIdAndReceiverId(String senderId, String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findBySenderIdAndReceiverId(senderId, receiverId)) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndState(LocalDateTime transactionDate, TxState state) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByTransactionDateAndState(transactionDate, state)) transactionDTOs.add(transactionToTransactionDTO(transaction));
		return transactionDTOs;
	}

}
