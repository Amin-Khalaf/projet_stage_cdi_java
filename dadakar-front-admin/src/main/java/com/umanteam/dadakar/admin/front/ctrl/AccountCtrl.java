package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;

@Controller
@RequestMapping(value={"/admin"})
public class AccountCtrl {

	@Autowired
	private IAccountService accountService;
	
	@RequestMapping(value={"/", "index"})
	public String index(Model model){
		model.addAttribute("adminAccounts", accountService.findAdminsAndSuperUsers());
		return "admin/index";
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST)
	public String create(@ModelAttribute("accountForm") Account account){
		System.out.println(account);
		account = accountService.add(account);
		return "redirect:/admin/index";
	}
}
