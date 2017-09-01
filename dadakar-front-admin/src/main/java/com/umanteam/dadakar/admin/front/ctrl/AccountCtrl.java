package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;

@Controller
@RequestMapping(value={"/admin"})
public class AccountCtrl {

	@Autowired
	IAccountService accountService;
	
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("adminAccounts", accountService.findAdminsAndSuperUsers());
		return "admin/index";
	}
}
