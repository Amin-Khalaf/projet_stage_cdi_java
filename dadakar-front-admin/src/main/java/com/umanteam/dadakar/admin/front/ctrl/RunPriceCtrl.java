package com.umanteam.dadakar.admin.front.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umanteam.dadakar.admin.front.dto.RunPrice;
import com.umanteam.dadakar.admin.front.service.interfaces.IRunPriceService;
import com.umanteam.dadakar.admin.front.validators.RunPriceValidator;

@Controller
@RequestMapping(value = { "/price" })
public class RunPriceCtrl {

	@Autowired
	private IRunPriceService runpriceService;

	// Set a form validator
	@Autowired
	RunPriceValidator runpriceValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(runpriceValidator);
	}

	@RequestMapping(value = { "/", "index" })
	public String index(Model model) {
		model.addAttribute("prices", runpriceService.findAll());
		model.addAttribute("runpriceForm", new RunPrice());
		return "price/index";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String create(@ModelAttribute("runpriceForm") @Validated RunPrice runprice, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			if (runprice.getRunPriceId() == null || runprice.getRunPriceId().equals("")) {
				// create a new price
				model.addAttribute("prices", runpriceService.findAll());
				return "price/index";
			} else {
				// update
				model.addAttribute("prices", runpriceService.findAll());
				return "price/index";
			}
		}
		
		if (runprice.getRunPriceId() == null || runprice.getRunPriceId().equals("")) {
			// create a new price
			runprice.setRunPriceId(null);
			runprice = runpriceService.add(runprice);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("message", "Prix ajouté");
		} else {
			// update price
			runprice = runpriceService.update(runprice);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("message", "Prix mis à jour");
		}
		model.addAttribute("prices", runpriceService.findAll());
		model.addAttribute("runpriceForm", new RunPrice());
		return "redirect:/price/index";
	}

	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable("id") String id, Model model, final RedirectAttributes redirectAttributes) {
		RunPrice runprice = runpriceService.findById(id);
		if (runprice == null) {
			redirectAttributes.addFlashAttribute("message", "Impossible d'accéder à l'enregistrement");
			return "redirect:/price/index";
		}
		model.addAttribute("prices", runpriceService.findAll());
		model.addAttribute("runpriceForm", runprice);
		return "/price/index";
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable("id") String id, Model model, final RedirectAttributes redirectAttributes) {
		runpriceService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Enregistrement supprimé");
		return "redirect:/price/index";
	}

}
