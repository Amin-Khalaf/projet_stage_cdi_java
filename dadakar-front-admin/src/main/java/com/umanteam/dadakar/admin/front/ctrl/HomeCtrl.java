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
		return "index";
	}
	
	@RequestMapping(value="/connect", method=RequestMethod.POST)
	public String connect(@ModelAttribute("connectForm") Connect connect) {
		
		//TODO: interface de connexion
		
		return null;
		
	}
}
