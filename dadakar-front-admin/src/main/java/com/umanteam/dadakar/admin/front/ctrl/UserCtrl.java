package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.dto.User;
import com.umanteam.dadakar.admin.front.service.implementation.UserService;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;

@Controller
@RequestMapping(value="/metier")
public class UserCtrl {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IAccountService accountService;
	
	@RequestMapping(value= {"/", "index"})
	public String index(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user/index";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String edit(@PathVariable("id") String id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "user/edit";
	}
	
	@RequestMapping(value="/bannish/{id}")
	public String bannish(@PathVariable("id") String id) {
		User user = userService.findById(id);
		Account account = accountService.findById(user.getAccount().getAccountId());
		account.setBanned(true);
		account = accountService.update(account);
		user.setAccount(account);
		userService.update(user);
		return "redirect:/user/index";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute("updateForm") User user) {
		userService.update(user);
		return "redirect:/user/index";
	}

}
