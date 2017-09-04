package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.dto.Bannished;
import com.umanteam.dadakar.admin.front.dto.User;
import com.umanteam.dadakar.admin.front.service.implementation.UserService;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;

@Controller
@RequestMapping(value="/user")
public class UserCtrl {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IAccountService accountService;
	
	@Value("${img.path}")
	private String imgPath;
	
	@RequestMapping(value={"/", "index"})
	public ModelAndView index(Model model) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("imgPath", imgPath);
		return new ModelAndView("user/index", "bannished", new Bannished());
	}
	
	@RequestMapping(value="/message/{id}")
	public String userMessage(@PathVariable("id") String id, Model model) {
		model.addAttribute("userid", id);
		return "user/message";
	}
	
	@RequestMapping(value="/bannish/{id}")
	public String bannish(@PathVariable("id") String id) {
		User user = userService.findById(id);
		Account account = accountService.findById(user.getAccount().getAccountId());
		account.setBanned(!account.isBanned());
		account = accountService.update(account);
		user.setAccount(account);
		userService.update(user);
		return "redirect:/user/";
	}
	
	@RequestMapping(value="/bannishall", method=RequestMethod.POST)
	public ModelAndView bannishAll(@ModelAttribute Bannished bannished) {
		ModelAndView view = new ModelAndView("redirect:/user/");
		view.addObject("bannished", bannished);
		for(String id: bannished.getBanned()) {
			Account account = accountService.findById(id);
			System.out.println(account);
			User user = userService.findByAccountUsername(account.getUsername());
			System.out.println(user);
			account.setBanned(!account.isBanned());
			account = accountService.update(account);
			System.out.println(account);
			user.setAccount(account);
			userService.update(user);
		}
		return view;
	}
	
}
