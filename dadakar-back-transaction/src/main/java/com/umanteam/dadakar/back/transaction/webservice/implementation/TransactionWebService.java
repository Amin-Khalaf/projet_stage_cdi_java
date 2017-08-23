package com.umanteam.dadakar.back.transaction.webservice.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.back.transaction.dto.TransactionDTO;
import com.umanteam.dadakar.back.transaction.enums.TxState;
import com.umanteam.dadakar.back.transaction.service.interfaces.ITransactionService;
import com.umanteam.dadakar.back.transaction.webservice.interfaces.ITransactionWebService;

@RestController
@RequestMapping("${appli.path}/transactions")
public class TransactionWebService implements ITransactionWebService {
	
	@Autowired
	private ITransactionService transactionService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public TransactionDTO add(@RequestBody TransactionDTO transactionDTO) { // OK
		return transactionService.add(transactionDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public TransactionDTO update(@RequestBody TransactionDTO transactionDTO) { // OK
		return transactionService.update(transactionDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") Integer id) { // OK
		transactionService.delete(id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findAll() { // OK
		List<TransactionDTO> transactions = transactionService.findAll();
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	@Override
	public TransactionDTO findById(@PathVariable("id") Integer id) { // OK
		return transactionService.findById(id);
	}

	@RequestMapping(value="/txnumber:{txnumber}", method=RequestMethod.GET)
	@Override
	public TransactionDTO findBytransactionNumber(@PathVariable("txnumber") String transactionNumber) { // OK
		return transactionService.findBytransactionNumber(transactionNumber);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/txdate:{txdate}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findByTransactionDate(@PathVariable("txdate") String transactionDate) { // OK
		/* Attention, chaine à formatter yyyy-MM-ddTHH:mm:ss */
		List<TransactionDTO> transactions = transactionService.findByTransactionDate(LocalDateTime.parse(transactionDate));
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/sid:{sid}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findBySenderId(@PathVariable("sid") String senderId) { // OK
		List<TransactionDTO> transactions = transactionService.findBySenderId(senderId);
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/rid:{rid}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findByReceiverId(@PathVariable("rid") String receiverId) { // OK
		List<TransactionDTO> transactions = transactionService.findByReceiverId(receiverId);
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/state:{state}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findByState(@PathVariable("state") String state) { // OK
		List<TransactionDTO> transactions = transactionService.findByState(TxState.valueOf(state));
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/txdate:{txdate}/sid:{sid}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findByTransactionDateAndSenderId(@PathVariable("txdate") String transactionDate, @PathVariable("sid") String senderId) { // OK
		List<TransactionDTO> transactions = transactionService.findByTransactionDateAndSenderId(LocalDateTime.parse(transactionDate), senderId);
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/txdate:{txdate}/rid:{rid}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findByTransactionDateAndReceiverId(@PathVariable("txdate") String transactionDate, @PathVariable("rid") String receiverId) { // OK
		List<TransactionDTO> transactions = transactionService.findByTransactionDateAndReceiverId(LocalDateTime.parse(transactionDate), receiverId);
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/sid:{sid}/rid:{rid}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findBySenderIdAndReceiverId(@PathVariable("sid") String senderId, @PathVariable("rid") String receiverId) { // OK
		List<TransactionDTO> transactions = transactionService.findBySenderIdAndReceiverId(senderId, receiverId);
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/txdate:{txdate}/state:{state}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TransactionDTO>> findByTransactionDateAndState(@PathVariable("txdate") String transactionDate, @PathVariable("state") String state) { // OK
		List<TransactionDTO> transactions = transactionService.findByTransactionDateAndState(LocalDateTime.parse(transactionDate), TxState.valueOf(state));
		if(transactions.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}

}
