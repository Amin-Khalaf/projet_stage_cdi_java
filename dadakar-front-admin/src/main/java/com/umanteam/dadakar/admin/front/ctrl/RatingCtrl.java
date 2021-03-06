package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.umanteam.dadakar.admin.front.service.implementation.RatingService;

@Controller
@RequestMapping(value="/rating")
public class RatingCtrl {
	
	@Autowired
	private RatingService ratingService;
	
	@RequestMapping(value= {"/", "index"})
	public String index(Model model) {
		model.addAttribute("ratings", ratingService.findAll());
		return "rating/index";
	}

}
