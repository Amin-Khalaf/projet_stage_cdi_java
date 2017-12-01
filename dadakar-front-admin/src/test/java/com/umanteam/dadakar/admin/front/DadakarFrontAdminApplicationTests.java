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
import com.umanteam.dadakar.admin.front.dto.RunPrice;
import com.umanteam.dadakar.admin.front.enums.Role;
import com.umanteam.dadakar.admin.front.service.implementation.AccountService;
import com.umanteam.dadakar.admin.front.service.implementation.RunPriceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DadakarFrontAdminApplicationTests {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RunPriceService runpriceService;
	
	@Test
	public void contextLoads() {
	}

	/* Test account service */
	@Test
	public void testAddAccount(){
		Account account = new Account("testuser1", "testpassword1", Role.USER);
		account = accountService.add(account);
		assertNotNull(account.getAccountId());
	}

	@Test
	public void testfindAccountbyId(){
		Account account = new Account("testuser2", "testpassword2", Role.USER);
		account = accountService.add(account);
		Account account2 = accountService.findById(account.getAccountId());
		assertNotNull(account2.getAccountId());
	}

	@Test
	public void testUpdateAccount(){
		Account account = new Account("testuser3", "testpassword3", Role.USER);
		account = accountService.add(account);
		account.setBanned(true);
		account = accountService.update(account);
		assertTrue(account.isBanned());
	}

	@Test
	public void testfindAccountbyUsername(){
		Account account2 = accountService.findByUsername("testuser1");
		assertNotNull(account2);
	}

	@Test
	public void testDeleteAccount(){
		Account account = new Account("testuser4", "testpassword4", Role.USER);
		account = accountService.add(account);
		String id = account.getAccountId();
		accountService.delete(id);
		Account account2 = accountService.findById(id);
		assertNull(account2);

		// save other test cases
		account = new Account("testuser5", "testpassword5", Role.USER);
		account = accountService.add(account);
		account.setBanned(true);
		account = accountService.update(account);

		account = new Account("testuser6", "testpassword6", Role.USER);
		account = accountService.add(account);
		account.setDeleted(true);
		account = accountService.update(account);

		account = new Account("testadmin7", "testpassword7", Role.ADMIN);
		account = accountService.add(account);
		account.setDeleted(true);
		account = accountService.update(account);
	}

	@Test
	public void testFindAllAccount(){
		List<Account> accounts = accountService.findAll();
		assertNotNull(accounts.get(0));
	}

	@Test
	public void testFindUserAccount(){
		List<Account> accounts = accountService.findUsers();
		assertEquals(accounts.get(0).getRole(), Role.USER);
		
	}

	@Test
	public void testFindAdminAccount(){
		List<Account> accounts = accountService.findAdmins();
		assertEquals(accounts.get(0).getRole(), Role.ADMIN);
	}

	@Test
	public void testFindSuperuserAccount(){
		List<Account> accounts = accountService.findSuperUsers();
		assertEquals(accounts.get(0).getRole(), Role.SUPERUSER);
	}

	@Test
	public void testFindAdminAndSuperAccount(){
		List<Account> accounts = accountService.findAdminsAndSuperUsers();
		assertNotNull(accounts);
	}

	@Test
	public void testFindBannedAccount(){
		List<Account> accounts = accountService.findBanned();
		assertEquals(accounts.get(0).isBanned(), true);
	}

	@Test
	public void testFindNotBannedAccount(){
		List<Account> accounts = accountService.findNotBanned();
		assertEquals(accounts.get(0).isBanned(), false);
	}

	@Test
	public void testFindDeletedAccount(){
		List<Account> accounts = accountService.findDeleted();
		assertEquals(accounts.get(0).isDeleted(), true);
	}

	@Test
	public void testFindDeletedUserAccount(){
		List<Account> accounts = accountService.findUsersDeleted();
		assertEquals(accounts.get(0).isDeleted(), true);
	}

	@Test
	public void testFindDeletedAdminAccount(){
		List<Account> accounts = accountService.findAdminsDeleted();
		assertEquals(accounts.get(0).isDeleted(), true);
	}

	@Test
	public void testFindNotDeletedAccount(){
		List<Account> accounts = accountService.findNotDeleted();
		assertEquals(accounts.get(0).isDeleted(), false);
	}

	@Test
	public void testFindNotDeletedUserAccount(){
		List<Account> accounts = accountService.findUsersNotDeleted();
		assertEquals(accounts.get(0).isDeleted(), false);
	}

	@Test
	public void testFindNotDeletedAdminAccount(){
		List<Account> accounts = accountService.findAdminsNotDeleted();
		assertEquals(accounts.get(0).isDeleted(), false);
	}
	
	/* Test runprice service */
	@Test
	public void testAddRunPrice(){
		RunPrice runprice = new RunPrice(5, 1.5, 2.5, 0.5, 0.05);
		runprice = runpriceService.add(runprice);
		assertNotNull(runprice.getRunPriceId());
		runpriceService.delete(runprice.getRunPriceId());
	}
	
	@Test
	public void testUpdateRunPrice(){
		RunPrice runprice = new RunPrice(5, 1.5, 2.5, 0.5, 0.05);
		runprice = runpriceService.add(runprice);
		runprice.setMaxPrice(2.0);
		runprice = runpriceService.update(runprice);
		assertEquals(2.0, runprice.getMaxPrice(), 0);
		runpriceService.delete(runprice.getRunPriceId());
	}
	
	@Test
	public void testDeleteRunPrice(){
		RunPrice runprice = new RunPrice(5, 1.5, 2.5, 0.5, 0.05);
		runprice = runpriceService.add(runprice);
		String id = runprice.getRunPriceId();
		runpriceService.delete(id);
		runprice = runpriceService.findById(id);
		assertNull(runprice);
	}
	
	@Test
	public void testFindAllRunPrice(){
		List<RunPrice> runprices = runpriceService.findAll();
		assertNotNull(runprices.get(0));
	}
	
	@Test
	public void testFindRunPriceById(){
		RunPrice runprice = new RunPrice(5, 1.5, 2.5, 0.5, 0.05);
		runprice = runpriceService.add(runprice);
		String id = runprice.getRunPriceId();
		runprice = null;
		runprice = runpriceService.findById(id);
		assertNotNull(runprice);
	}
	
	@Test
	public void testFindRunPriceByPower(){
		RunPrice runprice = runpriceService.findByPower(4);
		System.out.println(runprice);
		assertNotNull(runprice);
	}
}
