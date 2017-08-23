package com.umanteam.dadakar.back.transaction.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.transaction.dto.TransactionDTO;
import com.umanteam.dadakar.back.transaction.entities.Transaction;
import com.umanteam.dadakar.back.transaction.enums.TxState;
import com.umanteam.dadakar.back.transaction.repository.TransactionRepository;
import com.umanteam.dadakar.back.transaction.service.interfaces.ITransactionService;

@Service("transactionService")
public class TransactionService implements ITransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public TransactionDTO add(TransactionDTO transactionDTO) {
		Transaction transaction = new Transaction();
		BeanUtils.copyProperties(transactionDTO, transaction);
		transaction = transactionRepository.saveAndFlush(transaction);
		BeanUtils.copyProperties(transaction, transactionDTO);
		return transactionDTO;
	}

	@Override
	public TransactionDTO update(TransactionDTO transactionDTO) {
		return add(transactionDTO);
	}

	@Override
	public void delete(Integer id) {
		transactionRepository.delete(id);
	}

	@Override
	public List<TransactionDTO> findAll() {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findAll()) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public TransactionDTO findById(Integer id) {
		TransactionDTO transactionDTO = new TransactionDTO();
		Transaction transaction = transactionRepository.getOne(id);
		BeanUtils.copyProperties(transaction, transactionDTO);
		return transactionDTO;
	}

	@Override
	public TransactionDTO findBytransactionNumber(String transactionNumber) {
		TransactionDTO transactionDTO = new TransactionDTO();
		Transaction transaction = transactionRepository.findBytransactionNumber(transactionNumber);
		BeanUtils.copyProperties(transaction, transactionDTO);
		return transactionDTO;
	}

	@Override
	public List<TransactionDTO> findByTransactionDate(LocalDateTime transactionDate) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByTransactionDate(transactionDate)) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findBySenderId(String senderId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findBySenderId(senderId)) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByReceiverId(String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByReceiverId(receiverId)) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByState(TxState state) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByState(state)) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndSenderId(LocalDateTime transactionDate, String senderId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByTransactionDateAndSenderId(transactionDate, senderId)) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndReceiverId(LocalDateTime transactionDate, String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByTransactionDateAndReceiverId(transactionDate, receiverId)) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findBySenderIdAndReceiverId(String senderId, String receiverId) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findBySenderIdAndReceiverId(senderId, receiverId)) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public List<TransactionDTO> findByTransactionDateAndState(LocalDateTime transactionDate, TxState state) {
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction: transactionRepository.findByTransactionDateAndState(transactionDate, state)) {
			TransactionDTO transactionDTO = new TransactionDTO();
			BeanUtils.copyProperties(transaction, transactionDTO);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

}
