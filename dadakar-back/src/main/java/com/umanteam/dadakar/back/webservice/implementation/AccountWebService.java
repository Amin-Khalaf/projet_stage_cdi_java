package com.umanteam.dadakar.back.webservice.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.back.service.interfaces.IAccountService;
import com.umanteam.dadakar.back.webservice.interfaces.IAccountWebService;

@RestController
@RequestMapping("${appli.path}/accounts")
@CrossOrigin(origins="*")
public class AccountWebService implements IAccountWebService {
	
	@Autowired
	private IAccountService accountService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public AccountDTO add(@RequestBody AccountDTO accountDTO) { // OK
		return accountService.add(accountDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public AccountDTO update(@RequestBody AccountDTO accountDTO) { // OK
		return accountService.update(accountDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) { // OK
		accountService.delete(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findAll() { // OK
		List<AccountDTO> accounts = accountService.findAll();
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public AccountDTO findById(@PathVariable("id") String id) { // OK
		return accountService.findById(id);
	}
	
	@RequestMapping(value="/username:{username}", method=RequestMethod.GET)
	@Override
	public AccountDTO findByUsername(@PathVariable("username") String username) { // OK
		return accountService.findByUsername(username);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/users", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findUsers() { // OK
		List<AccountDTO> accounts = accountService.findByRole(Role.USER);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/admins", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findAdmins() { // OK
		List<AccountDTO> accounts = accountService.findByRole(Role.ADMIN);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/superusers", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findSuperUsers() { // OK
		List<AccountDTO> accounts = accountService.findByRole(Role.SUPERUSER);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/banned:{banned}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findByBanned(@PathVariable("banned") boolean banned) { // OK
		List<AccountDTO> accounts = accountService.findByBanned(banned);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/deleted:{deleted}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findByDeleted(@PathVariable("deleted") boolean deleted) { // OK
		List<AccountDTO> accounts = accountService.findByDeleted(deleted);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

}
