package com.umanteam.dadakar.admin.front.ctrl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.dto.Bannished;
import com.umanteam.dadakar.admin.front.dto.Message;
import com.umanteam.dadakar.admin.front.dto.User;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;
import com.umanteam.dadakar.admin.front.service.interfaces.IMessageService;
import com.umanteam.dadakar.admin.front.service.interfaces.IUserService;

@Controller
@RequestMapping(value="/plainte")
public class ComplaintCtrl {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IMessageService msgService;
	
	@RequestMapping(value= {"/", "index"})
	public ModelAndView index(Model model) {
		model.addAttribute("complaints", userService.getComplaint());
		return new ModelAndView("plainte/index", "bannished", new Bannished());
	}
	
	@RequestMapping(value="/message/{sid}:{rid}")
	public String userMessage(@PathVariable("sid") String sid, @PathVariable("rid") String rid, Model model) {
		LocalDateTime now = LocalDateTime.now();
		String month = now.getMonthValue() < 10 ? "0" + now.getMonthValue() : "" + now.getMonthValue() ;
		String day = now.getDayOfMonth() < 10 ? "0" + now.getDayOfMonth() : "" + now.getDayOfMonth();
		String hours = now.getHour() < 10 ? "0" + now.getHour() : "" + now.getHour();
		String minutes = now.getMinute() < 10 ? "0" + now.getMinute() : "" + now.getMinute();
		String date = now.getYear() + "-" + month + "-" + day + " " + hours + ":" + minutes;
		Message message = new Message(sid, rid, date, "Problème de profil", "");
		model.addAttribute("message", message);
		return "plainte/message";
	}
	
	@RequestMapping(value="/send", method= RequestMethod.POST)
	public String send(@ModelAttribute("message") Message message, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "plainte/message";
		}
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "le message à bien été envoyé");	
		msgService.add(message);
		
		return "redirect:/plainte/";
	}
	
	@RequestMapping(value="/bannish/{id}")
	public String bannish(@PathVariable("id") String id, final RedirectAttributes redirectAttributes) {
		User user = userService.findById(id);
		Account account = accountService.findById(user.getAccount().getAccountId());
		account.setBanned(!account.isBanned());
		account = accountService.update(account);
		user.setAccount(account);
		userService.update(user);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "l'utilisateur à bien été " + (account.isBanned() ? "banni" : "débanni"));	
		return "redirect:/plainte/";
	}
	
	@RequestMapping(value="/bannishall", method=RequestMethod.POST)
	public RedirectView bannishAll(@ModelAttribute Bannished bannished, final RedirectAttributes redirectAttributes) {
		RedirectView view = new RedirectView("/plainte/");
		view.addStaticAttribute("bannished", bannished);
		for(String id: bannished.getBanned()) {
			Account account = accountService.findById(id);
			User user = userService.findByAccountUsername(account.getUsername());
			account.setBanned(!account.isBanned());
			account = accountService.update(account);
			user.setAccount(account);
			userService.update(user);
		}
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "le status banni des utilisateurs séléctionnés à bien été inversé");	
		return view;
	}
	
}
