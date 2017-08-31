package com.umanteam.dadakar.admin.front;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.enums.Role;
import com.umanteam.dadakar.admin.front.service.implementation.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DadakarFrontAdminApplicationTests {
	
	@Autowired
	private AccountService accountService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testAccountService(){
		Account account = new Account("testuser1", "testpassword1", Role.USER);
		account = accountService.add(account);
		String id = account.getAccountId();
		assertNotNull(id);

		account.setBanned(true);
		account = accountService.update(account);
		assertTrue(account.isBanned());
		
		Account account2 = accountService.findById(account.getAccountId());
		assertNotNull(account2.getAccountId());

		account2 = null;
		account2 = accountService.findByUsername(account.getUsername());
		assertNotNull(account2);
		
		accountService.delete(id);
		account2 = null;
		account2 = accountService.findById(id);
		assertNull(account2);

		// save other test cases
		account = new Account("testuser2", "testpassword2", Role.USER);
		account = accountService.add(account);
		account.setBanned(true);
		account = accountService.update(account);

		account = new Account("testuser2", "testpassword2", Role.USER);
		account = accountService.add(account);
		account.setDeleted(true);
		account = accountService.update(account);

		account = new Account("testadmin1", "testpassword2", Role.ADMIN);
		account = accountService.add(account);
		account.setDeleted(true);
		account = accountService.update(account);

		List<Account> accounts = accountService.findAll();
		assertNotNull(accounts.get(0));
		
		accounts = accountService.findUsers();
		assertEquals(accounts.get(0).getRole(), Role.USER);
		
		accounts = accountService.findAdmins();
		assertEquals(accounts.get(0).getRole(), Role.ADMIN);

		accounts = accountService.findSuperUsers();
		assertEquals(accounts.get(0).getRole(), Role.SUPERUSER);

		accounts = accountService.findAdminsAndSuperUsers();
		assertNotNull(accounts);

		accounts = accountService.findBanned();
		assertEquals(accounts.get(0).isBanned(), true);

		accounts = accountService.findNotBanned();
		assertEquals(accounts.get(0).isBanned(), false);

		accounts = accountService.findDeleted();
		assertEquals(accounts.get(0).isDeleted(), true);

		accounts = accountService.findUsersDeleted();
		assertEquals(accounts.get(0).isDeleted(), true);

		accounts = accountService.findAdminsDeleted();
		assertEquals(accounts.get(0).isDeleted(), true);

		accounts = accountService.findNotDeleted();
		assertEquals(accounts.get(0).isDeleted(), false);

		accounts = accountService.findUsersNotDeleted();
		assertEquals(accounts.get(0).isDeleted(), false);

		accounts = accountService.findAdminsNotDeleted();
		assertEquals(accounts.get(0).isDeleted(), false);

	}
}
