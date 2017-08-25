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
		return accountService.addOrUpdate(accountDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public AccountDTO update(@RequestBody AccountDTO accountDTO) { // OK
		return accountService.addOrUpdate(accountDTO);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/adminsandsuperusers", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findAdminsAndSuperUsers() {
		List<AccountDTO> accounts = accountService.findAdminsAndSuperUsers();
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/banned", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findBanned() {
		List<AccountDTO> accounts = accountService.findByBanned(true);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/notbanned", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findNotBanned() {
		List<AccountDTO> accounts = accountService.findByBanned(false);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/deleted", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findDeleted() {
		List<AccountDTO> accounts = accountService.findByDeleted(true);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/deleted:users", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findUsersDeleted() {
		List<AccountDTO> accounts = accountService.findByDeletedAndRole(true, Role.USER);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/deleted:admins", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findAdminsDeleted() {
		List<AccountDTO> accounts = accountService.findByDeletedAndRole(true, Role.ADMIN);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/notdeleted", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findNotDeleted() {
		List<AccountDTO> accounts = accountService.findByDeleted(false);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/notdeleted:users", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findUsersNotDeleted() {
		List<AccountDTO> accounts = accountService.findByDeletedAndRole(false, Role.USER);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/notdeleted:admins", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AccountDTO>> findAdminsNotDeleted() {
		List<AccountDTO> accounts = accountService.findByDeletedAndRole(false, Role.ADMIN);
		if(accounts.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

}
