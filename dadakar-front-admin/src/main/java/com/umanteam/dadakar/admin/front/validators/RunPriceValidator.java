package com.umanteam.dadakar.admin.front.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.umanteam.dadakar.admin.front.dto.RunPrice;
import com.umanteam.dadakar.admin.front.service.interfaces.IRunPriceService;

@Component
public class RunPriceValidator implements Validator {

	@Autowired
	IRunPriceService runpriceService;
	
	@Override
	public boolean supports(Class<?> arg0) {
		return RunPrice.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RunPrice runprice = (RunPrice) target;
		
		if (runprice.getPower() <= 0) {
			errors.rejectValue("power", "NotEmpty.runpriceForm.power", "La puissance doit être un nombre entier supérieur à 0");
		}
		if (runprice.getStartingPrice() <= 0) {
			errors.rejectValue("startingPrice", "NotEmpty.runpriceForm.startingPrice", "Le prix doit être un nombre avec ou sans virgule supérieur à 0");
		}
		if (runprice.getMinPrice() <= 0) {
			errors.rejectValue("minPrice", "NotEmpty.runpriceForm.minPrice", "Le prix minimum doit être un nombre avec ou sans virgule supérieur à 0");
		}
		if (runprice.getMaxPrice() <= 0) {
			errors.rejectValue("maxPrice", "NotEmpty.runpriceForm.maxPrice", "Le prix maximum doit être un nombre avec ou sans virgule supérieur à 0");
		}
		if (runprice.getRate() <= 0 || runprice.getRate() >= 1) {
			errors.rejectValue("rate", "NotEmpty.runpriceForm.rate", "Le taux de perception doit être un nombre à virgule entre 0 et 1");
		}
		// verify another price with same power doesn't already exists
		RunPrice verifPower = runpriceService.findByPower(runprice.getPower());
		if (verifPower != null && (
				(runprice.getRunPriceId() == null || runprice.getRunPriceId().equals("")) || 
				(!verifPower.getRunPriceId().equals(runprice.getRunPriceId())))) {
			errors.rejectValue("power", "AlreadyExists.runpriceForm.power", "Un enregistrement pour cette puissance existe déjà");
		}
	}

}
