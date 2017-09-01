package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		account = accountService.add(account);
		return "redirect:/admin/index";
	}
	
	@RequestMapping(value="edit/{id}")
	public String edit(@PathVariable String id, Model model){
		model.addAttribute("account", accountService.findById(id));
		return "admin/edit";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute("accountForm") Account account){
		account = accountService.update(account);
		return "redirect:/admin/index";
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable("id") String id) {
		Account account = accountService.findById(id);
		account.setDeleted(true);
		accountService.update(account);
		return "redirect:/admin/index";
	}
}
