package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;

@Controller
@RequestMapping(value="/account")
public class AccountCtrl {

	@Autowired
	IAccountService accountService;
	
	@RequestMapping(value={"/","index"})
	public String adminpage(Model model){
		model.addAttribute("adminAccounts", accountService.findAdminsAndSuperUsers());
		return "account/index";
	}
}
