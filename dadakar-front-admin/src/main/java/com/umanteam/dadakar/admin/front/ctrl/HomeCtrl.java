package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umanteam.dadakar.admin.front.dto.Connect;

@Controller
public class HomeCtrl {
	
	@RequestMapping({"/", "index"})
	public String index(Model model) {
		return "redirect:/user/index";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		
		//TODO: interface de connexion
		
		return "login";
		
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String connect(@ModelAttribute("loginForm") Connect connect) {
		
		//TODO: interface de connexion
		
		return "login";
		
	}
}
