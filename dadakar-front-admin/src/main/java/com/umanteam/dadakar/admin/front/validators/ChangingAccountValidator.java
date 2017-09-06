package com.umanteam.dadakar.admin.front.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.dto.ChangingAccount;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;

@Component
public class ChangingAccountValidator implements Validator {
	
	@Autowired
	private IAccountService accountService;

	@Override
	public boolean supports(Class<?> arg0) {
		return ChangingAccount.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ChangingAccount changingAccount = (ChangingAccount) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "NotEmpty.passChangeForm.oldPassword", "Veuillez renseigner l'ancien mot de passe");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "NotEmpty.passChangeForm.newPassword", "Veuillez renseigner le nouveau mot de passe");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.passChangeForm.confirmPassword", "Veuillez confirmer le nouveau mot de passe");
		if(!changingAccount.getNewPassword().equals(changingAccount.getConfirmPassword())) errors.rejectValue("confirmPassword", "NotEquals.passChangeForm.confirmPassword", "Les mots de passe ne correspondent pas");
		
		Account account = accountService.findById(changingAccount.getAccountId());
		if(account == null || !account.getPassword().equals(changingAccount.getOldPassword())) errors.rejectValue("oldPassword", "Unknown.passChangeForm.oldPassword", "Ancien mot de passe incorrect");
		
	}

}
