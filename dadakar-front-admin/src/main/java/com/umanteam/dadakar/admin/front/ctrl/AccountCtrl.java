package com.umanteam.dadakar.admin.front.ctrl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.dto.ChangingAccount;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;
import com.umanteam.dadakar.admin.front.validators.AccountValidator;
import com.umanteam.dadakar.admin.front.validators.ChangingAccountValidator;

@Controller
@RequestMapping(value={"/admin"})
public class AccountCtrl {

	@Autowired
	private IAccountService accountService;
	
	// set form validator
	@Autowired
	AccountValidator accountValidator;
	
	@Autowired
	private ChangingAccountValidator changingAccountValidator;
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(accountValidator);
//		binder.setValidator(changingAccountValidator);
//	}
	
	@RequestMapping(value={"/", "index"})
	public String index(Model model){
		model.addAttribute("adminAccounts", accountService.findAdminsAndSuperUsers());
		model.addAttribute("accountForm", new Account());
		return "admin/index";
	}
	
	@RequestMapping(value="save", method = RequestMethod.POST)
	public String create(@ModelAttribute("accountForm") Account account, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes){
		accountValidator.validate(account, result);
		if (result.hasErrors()){
			model.addAttribute("adminAccounts", accountService.findAdminsAndSuperUsers());
			return "admin/index";
		}
		redirectAttributes.addFlashAttribute("css", "success");
		if (account.getAccountId() == null || account.getAccountId().equals("")){
			// create mode
			account = accountService.add(account);
			redirectAttributes.addFlashAttribute("message", "Compte créé.");
		} else {
			// update mode
			Account temp = accountService.findById(account.getAccountId());
			if(!temp.getPassword().equals(account.getPassword())) account.setPassword(DigestUtils.sha1Hex(account.getPassword()));
			account = accountService.update(account);
			redirectAttributes.addFlashAttribute("message", "Compte mis à jour.");
		}
		return "redirect:/admin/index";
	}
	
	@RequestMapping(value="edit/{id}")
	public String edit(@PathVariable String id, Model model){
		model.addAttribute("accountForm", accountService.findById(id));
		model.addAttribute("adminAccounts", accountService.findAdminsAndSuperUsers());
//		return "admin/edit";
		return "admin/index";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute("accountForm") Account account){
		Account temp = accountService.findById(account.getAccountId());
		if(!temp.getPassword().equals(account.getPassword())) account.setPassword(DigestUtils.sha1Hex(account.getPassword()));
		account = accountService.update(account);
		return "redirect:/admin/index";
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable("id") String id, final RedirectAttributes redirectAttributes) {
		Account account = accountService.findById(id);
		account.setDeleted(true);
		accountService.update(account);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("message", "Compte supprimé");
		return "redirect:/admin/index";
	}
	
	@RequestMapping(value="/passwordchange/{url}/{id}")
	public String changePassword(@PathVariable("url") String url, @PathVariable("id") String id, Model model) {
		model.addAttribute("passChangeForm", new ChangingAccount(id, "", "", "", url));
		return "admin/passwordchange";
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.POST)
	public String change(@ModelAttribute("passChangeForm") ChangingAccount changingAccount, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		changingAccountValidator.validate(changingAccount, result);
		if(result.hasErrors()) return "admin/passwordchange";
		
		Account account = accountService.findById(changingAccount.getAccountId());
		account.setPassword(changingAccount.getNewPassword()); // passwords directly encrypt in ChangingAccount
		accountService.update(account);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("message", "Mot de passe modifié avec Succès");
		
		return "redirect:" + changingAccount.getUrl().replaceAll("-", "/");
	}
	
}
